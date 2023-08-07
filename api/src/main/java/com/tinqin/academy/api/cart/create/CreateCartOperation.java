package com.tinqin.academy.api.cart.create;

import com.tinqin.academy.api.base.OperationProcessor;

public interface CreateCartOperation extends OperationProcessor<CreateCartResponse, CreateCartRequest> {
    CreateCartResponse process(CreateCartRequest request);
}
