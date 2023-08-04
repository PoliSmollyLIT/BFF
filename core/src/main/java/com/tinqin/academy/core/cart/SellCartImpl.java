package com.tinqin.academy.core.cart;

import com.example.storage.api.operations.itemStorage.get.GetItemResponse;
import com.example.storage.restexport.StorageRestClient;
import com.tinqin.academy.api.cart.sell.SellCartOperation;
import com.tinqin.academy.api.cart.sell.SellCartRequest;
import com.tinqin.academy.api.cart.sell.SellCartResponse;
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.models.CartItem;
import com.tinqin.academy.persistence.repositories.CartRepository;
import com.tinqin.academy.persistence.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellCartImpl implements SellCartOperation {
    private final StorageRestClient storageRestClient;
    private final CartRepository cartRepository;

    @Override
    public SellCartResponse process(SellCartRequest request) {
        Cart cartFromRepository = cartRepository.findById(request.getCartID())
                .orElseThrow(()->new EntityNotFoundException("Cart with this ID not found"));

        cartFromRepository.getItems().stream()
                .forEach(item->
                {
                    GetItemResponse itemFromStrorage = storageRestClient.getItemById(request.getCartID().toString());
                    if (itemFromStrorage.getQuantity() >= item.getQuantity()) {
                        cartFromRepository.setPrice(cartFromRepository.getPrice() + (itemFromStrorage.getPrice() * item.getQuantity()));
                    }
                });
        //storageRestClient.

        return null;
    }
}
