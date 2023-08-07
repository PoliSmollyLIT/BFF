package com.tinqin.academy.core.cart;

import com.example.zooostore.api.operations.item.get.GetItemResponse;
import com.tinqin.academy.api.cart.addItem.AddItemOperation;
import com.tinqin.academy.api.cart.addItem.AddItemRequest;
import com.tinqin.academy.api.cart.addItem.AddItemResponse;
import com.tinqin.academy.api.cart.addItem.AddItemSingleItemResponse;
import com.tinqin.academy.api.item.get.GetItemInfoResponse;
import com.tinqin.academy.api.item.get.GetItemOperation;
import com.tinqin.academy.api.item.get.GetItemRequest;
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.models.CartItem;
import com.tinqin.academy.persistence.repositories.CartItemRepository;
import com.tinqin.academy.persistence.repositories.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddItemToCartCore implements AddItemOperation {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final GetItemOperation getItem;
    @Override
    public AddItemResponse process(AddItemRequest request) {
        Cart cartFromRepository = cartRepository.findById(request.getCartId())
                              .orElseThrow(()->new EntityNotFoundException("Cart with this ID not found"));
     GetItemInfoResponse itemResponse = getItem.process(
             GetItemRequest.builder()
             .itemID(request.getItemID())
             .build());
        CartItem item = CartItem.builder()
                .itemId(request.getItemID())
                .quantity(request.getQuantity())
                .price(itemResponse.getPrice())
                .cart(cartFromRepository)
                .build();
        cartItemRepository.save(item);
        cartFromRepository.getItems().add(item);
        cartRepository.save(cartFromRepository);
        return AddItemResponse.builder()
                .cartId(cartFromRepository.getId())
                .user(cartFromRepository.getUser())
                .items(getItems(cartFromRepository.getItems()))
                .build();
    }

    private List<AddItemSingleItemResponse> getItems(List<CartItem> items) {
        return items.stream()
                .map(item -> AddItemSingleItemResponse.builder()
                        .item(item.getId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .build()
                ).collect(Collectors.toList());
    }
}

