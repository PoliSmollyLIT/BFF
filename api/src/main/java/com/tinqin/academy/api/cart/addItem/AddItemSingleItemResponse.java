package com.tinqin.academy.api.cart.addItem;

import lombok.*;

import java.util.UUID;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemSingleItemResponse {
        private UUID item;
        private Integer quantity;
        private Double price;
}
