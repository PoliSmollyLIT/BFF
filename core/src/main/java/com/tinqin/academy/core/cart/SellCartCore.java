package com.tinqin.academy.core.cart;

import com.example.storage.api.operations.itemStorage.get.GetItemResponse;
import com.example.storage.api.operations.order.add.OrderAddOrderItem;
import com.example.storage.api.operations.order.add.OrderAddRequest;
import com.example.storage.api.operations.order.add.OrderAddResponse;
import com.example.storage.restexport.StorageRestClient;
import com.tinqin.academy.api.cart.get.GetCartOperation;
import com.tinqin.academy.api.cart.sell.SellCartOperation;
import com.tinqin.academy.api.cart.sell.SellCartRequest;
import com.tinqin.academy.api.cart.sell.SellCartResponse;
import com.tinqin.academy.api.codes.use.UserCodeOperation;
import com.tinqin.academy.api.codes.use.UserCodeRequest;
import com.tinqin.academy.api.codes.use.UserCodeResponse;
import com.tinqin.academy.api.invoice.GenerateInvoiceOperation;
import com.tinqin.academy.api.invoice.InvoiceRequest;
import com.tinqin.academy.api.invoice.InvoiceResponse;
import com.tinqin.academy.api.invoice.InvoiceSingleItem;
import com.tinqin.academy.core.invoice.InvoiceGenerator;
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.models.UserLevel;
import com.tinqin.academy.persistence.repositories.CartRepository;
import com.tinqin.academy.persistence.repositories.UserRepository;
import com.tinquinstore.zooostore.restexport.ZooStoreRestClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellCartCore implements SellCartOperation {
    private final StorageRestClient storageRestClient;
    private final ZooStoreRestClient zooStoreRestClient;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final GenerateInvoiceOperation invoiceGenerator;
    private final UserCodeOperation userCodeOperation;

    @SneakyThrows
    @Override
    public SellCartResponse process(SellCartRequest request) {
        Cart cartFromRepository = cartRepository.findById(request.getCartID())
                .orElseThrow(()->new EntityNotFoundException("Cart with this ID not found"));
        cartFromRepository.getItems().stream()
                .forEach(item ->{
                    cartFromRepository.setPrice(cartFromRepository.getPrice() + item.getPrice()*item.getQuantity());
                });

        User user = userRepository.findById(cartFromRepository.getUser())
                .orElseThrow(()-> new EntityNotFoundException("User with this ID not found"));

        UserCodeResponse userCode = userCodeOperation.process(UserCodeRequest.builder().userId(user.getId()).build());
        Double discount = cartFromRepository.getPrice() * (user.getUserLevel().getPercentOff() + userCode.getDiscount())/100;
        OrderAddResponse response = storageRestClient.addOrder(OrderAddRequest.builder()
                        .cartID(cartFromRepository.getId())
                        .user(cartFromRepository.getUser())
                        .items(cartFromRepository.getItems().stream()
                                .map(item -> OrderAddOrderItem.builder()
                                        .id(item.getItemId())
                                        .price(item.getPrice())
                                        .quantity(item.getQuantity())
                                        .build())
                                .collect(Collectors.toList()))
                        .price(cartFromRepository.getPrice())
                        .discount(discount)
                        .build());

        user.setCurrentPoints(user.getCurrentPoints() + cartFromRepository.getPrice().intValue());    // adds points for every 1 lev of the price of the cart
        if(user.getUserLevel().getMax()<user.getCurrentPoints()){
            int userLevelNumber = user.getUserLevel().getLevelNumber();
            UserLevel newUserLevel = user.getUserLevel().setLevelByLevelNumber( userLevelNumber +1);
            user.setUserLevel(UserLevel.valueOf(newUserLevel.name()));
        }
        userRepository.save(user);


        InvoiceRequest invoiceRequest = InvoiceRequest.builder()
                .userFistName(user.getFirstName())
                .userLastName(user.getLastName())
                .phone(user.getPhone())
                .orderId(cartFromRepository.getId())
                .dateMade( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(response.getResult())) // 2023-08-08 11:39:35.632766
                .price(cartFromRepository.getPrice())
                .discount(discount)
                .finalPrice(cartFromRepository.getPrice() - discount)
                .items(cartFromRepository.getItems().stream()
                        .map(item -> InvoiceSingleItem.builder()
                                .itemTitle(zooStoreRestClient.getItemById(item.getItemId().toString()).getTitle())
                                .price(item.getPrice())
                                .quantity(item.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        InvoiceResponse invoiceResponse = invoiceGenerator.process(invoiceRequest);
        ByteArrayInputStream bis = invoiceResponse.getResponse();

        return SellCartResponse.builder()
                .pdfFile(bis)
                .filename("invoice"+cartFromRepository.getId().toString())
                .build();
    }
}
