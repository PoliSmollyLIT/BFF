package com.tinqin.academy.api.cart.get;

import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCartSingleItemResponse {
    private UUID item;
    private Integer quantity;
    private Double price;
}
