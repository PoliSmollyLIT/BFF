package com.tinqin.academy.api.cart.delete;

import com.tinqin.academy.api.base.OperationProcessor;

public interface DeleteCartOperation extends OperationProcessor<DeleteCartResponse, DeleteCartRequest> {
    DeleteCartResponse process(DeleteCartRequest request);
}
