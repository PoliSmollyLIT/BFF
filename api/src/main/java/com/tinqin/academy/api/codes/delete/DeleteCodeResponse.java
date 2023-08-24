package com.tinqin.academy.api.codes.delete;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCodeResponse implements OperationResponse {
    private int discount;
}
