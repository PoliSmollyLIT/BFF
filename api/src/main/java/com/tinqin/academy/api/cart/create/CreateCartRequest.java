package com.tinqin.academy.api.cart.create;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartRequest implements OperationRequest {
    private UUID userId;
}
