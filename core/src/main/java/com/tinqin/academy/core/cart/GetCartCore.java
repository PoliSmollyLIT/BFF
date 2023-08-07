package com.tinqin.academy.core.cart;

import com.tinqin.academy.api.cart.get.GetCartOperation;
import com.tinqin.academy.api.cart.get.GetCartRequest;
import com.tinqin.academy.api.cart.get.GetCartResponse;
import com.tinqin.academy.api.cart.get.GetCartSingleItemResponse;
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
public class GetCartCore implements GetCartOperation {
    private final CartRepository cartRepository;
    @Override
    public GetCartResponse process(GetCartRequest request) {
        Cart cartFromRepository = cartRepository.findById(request.getCartId())
                .orElseThrow(()->new EntityNotFoundException("Cart with this ID not found"));
        return GetCartResponse.builder()
                .id(cartFromRepository.getId())
                .user(cartFromRepository.getUser())
                .items(getItems(cartFromRepository.getItems()))
                .build();
    }

    private List<GetCartSingleItemResponse> getItems(List<CartItem> items){
        return items.stream()
                .map(item-> GetCartSingleItemResponse.builder()
                        .item(item.getId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
