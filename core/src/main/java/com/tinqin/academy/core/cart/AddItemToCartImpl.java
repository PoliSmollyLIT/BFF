package com.tinqin.academy.core.cart;

import com.tinqin.academy.api.cart.addItem.AddItemOperation;
import com.tinqin.academy.api.cart.addItem.AddItemRequest;
import com.tinqin.academy.api.cart.addItem.AddItemResponse;
import com.tinqin.academy.api.cart.addItem.AddItemSingleItemResponse;
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.models.CartItem;
import com.tinqin.academy.persistence.repositories.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddItemToCartImpl implements AddItemOperation {

    private final CartRepository cartRepository;
    @Override
    public AddItemResponse process(AddItemRequest request) {
        Cart cartFromRepository = cartRepository.findById(request.getCartId())
                .orElseThrow(()->new EntityNotFoundException("Cart with this ID not found"));
        CartItem item = CartItem.builder()
                .id(request.getItemID())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
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

