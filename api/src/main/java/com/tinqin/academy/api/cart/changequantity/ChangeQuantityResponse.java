package com.tinqin.academy.api.cart.changequantity;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeQuantityResponse implements OperationResponse {
    private UUID cartId;
    private UUID user;
    private List<ChangeQuantitySingleItemResponse> items;
}
