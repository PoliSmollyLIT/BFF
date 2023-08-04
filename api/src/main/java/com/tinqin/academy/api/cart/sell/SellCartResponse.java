package com.tinqin.academy.api.cart.sell;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellCartResponse implements OperationResponse {
    private String response;
}
