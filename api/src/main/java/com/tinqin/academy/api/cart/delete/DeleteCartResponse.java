package com.tinqin.academy.api.cart.delete;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCartResponse implements OperationResponse {
    private String response;
}
