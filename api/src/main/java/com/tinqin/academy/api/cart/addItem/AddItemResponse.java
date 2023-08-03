package com.tinqin.academy.api.cart.addItem;

import com.tinqin.academy.api.base.OperationResponse;
import com.tinqin.academy.api.cart.get.GetCartSingleItemResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemResponse implements OperationResponse {
    private UUID cartId;
    private UUID user;
    private List<AddItemSingleItemResponse> items;
}
