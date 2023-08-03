package com.tinqin.academy.api.cart.get;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCartRequest implements OperationRequest {
    private UUID cartId;
}
