package com.tinqin.academy.api.codes.use;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCodeRequest implements OperationRequest {
    private UUID userId;
}
