package com.tinqin.academy.core.cart;

import com.tinqin.academy.api.cart.changequantity.ChangeQuantityOperation;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantityRequest;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantityResponse;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantitySingleItemResponse;
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
public class ChangeQuantityOfCartCore implements ChangeQuantityOperation {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public ChangeQuantityResponse process(ChangeQuantityRequest request) {
        Cart cartFromRepository = cartRepository.findById(request.getCartId())
                .orElseThrow(()->new EntityNotFoundException("Cart with this ID not found"));
        CartItem item = cartItemRepository.findCartItemByItemIdAndCart_Id(request.getItemId(), request.getCartId())
                .orElseThrow(()->new EntityNotFoundException("Item with this ID not found"));
        item.setQuantity(item.getQuantity() + request.getQuantity());
        if(item.getQuantity() == 0){
            cartFromRepository.getItems().remove(item);
            cartItemRepository.delete(item);
        }
        cartItemRepository.save(item);
        cartRepository.save(cartFromRepository);
        return ChangeQuantityResponse.builder()
                .cartId(cartFromRepository.getId())
                .user(cartFromRepository.getUser())
                .items(getItems(cartFromRepository.getItems()))
                .build();
    }


private List<ChangeQuantitySingleItemResponse> getItems(List<CartItem> items) {
    return items.stream()
            .map(item -> ChangeQuantitySingleItemResponse.builder()
                    .item(item.getId())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .build()
            ).collect(Collectors.toList());
}
}
