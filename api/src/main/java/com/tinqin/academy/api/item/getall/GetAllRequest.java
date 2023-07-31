package com.tinqin.academy.api.item.getall;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRequest implements OperationRequest {
    private String tagTitle;
}
