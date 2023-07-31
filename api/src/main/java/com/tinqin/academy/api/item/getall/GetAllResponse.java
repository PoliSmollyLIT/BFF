package com.tinqin.academy.api.item.getall;

import com.tinqin.academy.api.base.OperationResponse;
import com.tinqin.academy.api.item.get.GetItemInfoResponse;
import com.tinqin.academy.api.item.get.GetItemVendorResponse;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllResponse implements OperationResponse {
    private Set<GetAllSingleItemResponse> items;
}
