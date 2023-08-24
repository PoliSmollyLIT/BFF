package com.tinqin.academy.api.codes.use;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCodeResponse implements OperationResponse {
    private int discount;
}
