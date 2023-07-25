package com.tinqin.academy.api.item.get;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetItemRequest implements OperationRequest {
    private UUID itemID;
}
