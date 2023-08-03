package com.tinqin.academy.persistence.repositories;

import com.tinqin.academy.persistence.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
