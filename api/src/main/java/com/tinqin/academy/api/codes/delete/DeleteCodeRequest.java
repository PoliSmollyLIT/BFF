package com.tinqin.academy.api.codes.delete;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCodeRequest implements OperationRequest {
    private UUID userId;
}
