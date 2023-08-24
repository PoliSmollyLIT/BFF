package com.tinqin.academy.api.codes.addcodetouser;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCodeToUserResponse implements OperationResponse {
    private UUID id;
    private UUID userId;
    private String code;
    private Timestamp expiration;
}
