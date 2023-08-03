package com.tinqin.academy.api.cart.changequantity;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeQuantityRequest implements OperationRequest {
    private UUID cartId;
    private UUID itemId;
    private Integer quantity;
}
