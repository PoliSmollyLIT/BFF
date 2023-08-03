package com.tinqin.academy.api.cart.addItem;

import com.tinqin.academy.api.base.OperationProcessor;

public interface AddItemOperation extends OperationProcessor<AddItemResponse, AddItemRequest> {
    AddItemResponse process(AddItemRequest request);
}
