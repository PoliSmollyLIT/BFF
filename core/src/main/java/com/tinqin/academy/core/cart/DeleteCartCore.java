package com.tinqin.academy.core.cart;

import com.tinqin.academy.api.cart.delete.DeleteCartOperation;
import com.tinqin.academy.api.cart.delete.DeleteCartRequest;
import com.tinqin.academy.api.cart.delete.DeleteCartResponse;
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.repositories.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCartCore implements DeleteCartOperation {
    private final CartRepository cartRepository;
    @Override
    public DeleteCartResponse process(DeleteCartRequest request) {
        Cart cartFromRepository = cartRepository.findById(request.getCartID())
                .orElseThrow(()->new EntityNotFoundException("Cart with this ID not found"));
        cartRepository.delete(cartFromRepository);
        return DeleteCartResponse.builder()
                .response("Removed cart1")
                .build();
    }
}
