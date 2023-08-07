package com.tinqin.academy.api.cart.addItem;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest implements OperationRequest {
    private UUID cartId;
    private UUID itemID;
    private Integer quantity;
}
