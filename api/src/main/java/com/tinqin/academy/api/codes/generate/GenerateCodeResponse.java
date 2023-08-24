package com.tinqin.academy.api.codes.generate;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateCodeResponse implements OperationResponse {
    private String code;
}
