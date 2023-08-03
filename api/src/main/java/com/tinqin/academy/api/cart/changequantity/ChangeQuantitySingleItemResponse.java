package com.tinqin.academy.api.cart.changequantity;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeQuantitySingleItemResponse {
        private UUID item;
        private Integer quantity;
        private Double price;
}
