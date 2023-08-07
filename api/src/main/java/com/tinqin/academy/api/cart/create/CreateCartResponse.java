package com.tinqin.academy.api.cart.create;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartResponse implements OperationResponse {
    private UUID cartId;
}
