package com.tinqin.academy.api.codes.addcodetouser;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCodeToUserRequest implements OperationRequest {
    private UUID userId;
}
