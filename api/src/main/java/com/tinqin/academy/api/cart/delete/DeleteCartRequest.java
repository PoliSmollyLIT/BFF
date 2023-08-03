package com.tinqin.academy.api.cart.delete;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCartRequest implements OperationRequest {
    private UUID cartID;
}
