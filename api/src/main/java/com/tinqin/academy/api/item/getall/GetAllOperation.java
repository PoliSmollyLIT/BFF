package com.tinqin.academy.api.item.getall;

import com.tinqin.academy.api.base.OperationProcessor;

public interface GetAllOperation extends OperationProcessor<GetAllResponse, GetAllRequest> {
    GetAllResponse process(GetAllRequest request);
}
