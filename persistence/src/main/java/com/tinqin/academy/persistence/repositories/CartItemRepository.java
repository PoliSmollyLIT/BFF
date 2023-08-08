package com.tinqin.academy.persistence.repositories;

import com.tinqin.academy.persistence.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
   // Optional<CartItem> findCartItemByIdAndCart_Id(UUID itemId, UUID cartId);
    Optional<CartItem> findCartItemByItemIdAndCart_Id(UUID itemId, UUID cartId);
}
