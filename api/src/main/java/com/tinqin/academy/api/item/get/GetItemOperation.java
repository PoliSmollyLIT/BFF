package com.tinqin.academy.api.item.get;

import com.tinqin.academy.api.base.OperationProcessor;

public interface GetItemOperation extends OperationProcessor<GetItemInfoResponse, GetItemRequest> {
    GetItemInfoResponse process(GetItemRequest request);
}
