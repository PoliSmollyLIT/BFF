package com.tinqin.academy.api.cart.get;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCartResponse implements OperationResponse {
    private UUID id;
    private UUID user;
    private List<GetCartSingleItemResponse> items;
}
