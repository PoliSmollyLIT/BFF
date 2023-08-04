package com.tinqin.academy.api.cart.sell;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SellCartRequest implements OperationRequest {
    private UUID cartID;

}
