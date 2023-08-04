package com.tinqin.academy.api.cart.sell;

import com.tinqin.academy.api.base.OperationProcessor;

public interface SellCartOperation extends OperationProcessor<SellCartResponse, SellCartRequest> {
    SellCartResponse process(SellCartRequest request);
}
