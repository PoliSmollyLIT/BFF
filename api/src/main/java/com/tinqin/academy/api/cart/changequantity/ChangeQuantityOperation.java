package com.tinqin.academy.api.cart.changequantity;

import com.tinqin.academy.api.base.OperationProcessor;

public interface ChangeQuantityOperation extends OperationProcessor<ChangeQuantityResponse, ChangeQuantityRequest> {
    ChangeQuantityResponse process(ChangeQuantityRequest request);
}
