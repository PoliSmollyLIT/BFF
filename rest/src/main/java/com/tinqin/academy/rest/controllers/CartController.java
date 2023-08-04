package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.cart.addItem.AddItemRequest;
import com.tinqin.academy.api.cart.addItem.AddItemResponse;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantityRequest;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantityResponse;
import com.tinqin.academy.api.cart.delete.DeleteCartRequest;
import com.tinqin.academy.api.cart.delete.DeleteCartResponse;
import com.tinqin.academy.api.cart.get.GetCartRequest;
import com.tinqin.academy.api.cart.get.GetCartResponse;
import com.tinqin.academy.core.cart.AddItemToCartImpl;
import com.tinqin.academy.core.cart.DeleteCartImpl;
import com.tinqin.academy.core.cart.GetCartImpl;
import com.tinqin.academy.core.cart.ChangeQuantityOfCartImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart", description = "API for working with your cart")
@RequiredArgsConstructor
public class CartController {
    private final AddItemToCartImpl addItem;
    private final ChangeQuantityOfCartImpl changeQuantity;
    private final GetCartImpl getCart;
    private final DeleteCartImpl deleteCart;

    @DeleteMapping("/{id}")
    ResponseEntity<DeleteCartResponse> deleteCart(@PathVariable UUID id){
        DeleteCartRequest request = DeleteCartRequest.builder()
                .cartID(id)
                .build();
        return ResponseEntity.ok(deleteCart.process(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<GetCartResponse> getCart(@PathVariable UUID id){
        GetCartRequest request = GetCartRequest.builder()
                .cartId(id)
                .build();
        return ResponseEntity.ok(getCart.process(request));
    }

    @PostMapping("/")
    ResponseEntity<AddItemResponse> addItem(@RequestBody AddItemRequest request){
        return ResponseEntity.ok(addItem.process(request));
    }

    @PutMapping("/")
    ResponseEntity<ChangeQuantityResponse> changeQuantity(@RequestBody ChangeQuantityRequest request){
        return ResponseEntity.ok(changeQuantity.process(request));
    }


}
