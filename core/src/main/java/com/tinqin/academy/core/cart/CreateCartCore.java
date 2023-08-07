package com.tinqin.academy.core.cart;

import com.tinqin.academy.api.cart.create.CreateCartOperation;
import com.tinqin.academy.api.cart.create.CreateCartRequest;
import com.tinqin.academy.api.cart.create.CreateCartResponse;
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCartCore implements CreateCartOperation {
    private final CartRepository cartRepository;

    @Override
    public CreateCartResponse process(CreateCartRequest request) {
        Cart cart = Cart.builder()
                .user(request.getUserId())
                .build();
        cartRepository.save(cart);
       return CreateCartResponse.builder()
               .cartId(cart.getId())
               .build();
    }
}
