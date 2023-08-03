package com.tinqin.academy.api.cart.get;

import com.tinqin.academy.api.base.OperationProcessor;

public interface GetCartOperation extends OperationProcessor<GetCartResponse, GetCartRequest>{
    GetCartResponse process(GetCartRequest request);
}
