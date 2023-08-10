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
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.repositories.CartRepository;
import com.tinqin.academy.persistence.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellCartCore implements SellCartOperation {
    private final StorageRestClient storageRestClient;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

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

        Double discount = cartFromRepository.getPrice() * user.getUserLevel().getPercentOff()/100;
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

        user.setCurrentPoints(user.getCurrentPoints() + Integer.parseInt(String.valueOf(cartFromRepository.getPrice()))); // adds points for every 1 lev of the price of the cart
        if(user.getUserLevel().getMax()<user.getCurrentPoints()){
            user.setUserLevel(user.getUserLevel().setLevelByLevelNumber(user.getUserLevel().getLevelNumber()+1));
        }
        userRepository.save(user);
        return SellCartResponse.builder().response(response.getResult()).build();
    }
}
