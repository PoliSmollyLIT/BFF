package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.cart.addItem.AddItemOperation;
import com.tinqin.academy.api.cart.addItem.AddItemRequest;
import com.tinqin.academy.api.cart.addItem.AddItemResponse;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantityOperation;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantityRequest;
import com.tinqin.academy.api.cart.changequantity.ChangeQuantityResponse;
import com.tinqin.academy.api.cart.create.CreateCartOperation;
import com.tinqin.academy.api.cart.create.CreateCartRequest;
import com.tinqin.academy.api.cart.create.CreateCartResponse;
import com.tinqin.academy.api.cart.delete.DeleteCartOperation;
import com.tinqin.academy.api.cart.delete.DeleteCartRequest;
import com.tinqin.academy.api.cart.delete.DeleteCartResponse;
import com.tinqin.academy.api.cart.get.GetCartOperation;
import com.tinqin.academy.api.cart.get.GetCartRequest;
import com.tinqin.academy.api.cart.get.GetCartResponse;
import com.tinqin.academy.api.cart.sell.SellCartOperation;
import com.tinqin.academy.api.cart.sell.SellCartRequest;
import com.tinqin.academy.api.cart.sell.SellCartResponse;
import com.tinqin.academy.core.cart.AddItemToCartCore;
import com.tinqin.academy.core.cart.DeleteCartCore;
import com.tinqin.academy.core.cart.GetCartCore;
import com.tinqin.academy.core.cart.ChangeQuantityOfCartCore;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart", description = "API for working with your cart")
@RequiredArgsConstructor
public class CartController {
    private final AddItemOperation addItem;
    private final ChangeQuantityOperation changeQuantity;
    private final GetCartOperation getCart;
    private final DeleteCartOperation deleteCart;
    private final CreateCartOperation createCart;
    private final SellCartOperation sellCart;

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

    @PostMapping("/item")
    ResponseEntity<AddItemResponse> addItem(@RequestBody AddItemRequest request){
        return ResponseEntity.ok(addItem.process(request));
    }

    @PostMapping("/")
    ResponseEntity<CreateCartResponse> createCart(@RequestBody CreateCartRequest request){
        return ResponseEntity.ok(createCart.process(request));
    }

    @PutMapping("/")
    ResponseEntity<ChangeQuantityResponse> changeQuantity(@RequestBody ChangeQuantityRequest request){
        return ResponseEntity.ok(changeQuantity.process(request));
    }

    @PostMapping("/{id}")
    ResponseEntity<SellCartResponse> sellCart(@PathVariable UUID id){
        SellCartRequest request = SellCartRequest.builder()
                .cartID(id)
                .build();
        return ResponseEntity.ok(sellCart.process(request));
    }
}
