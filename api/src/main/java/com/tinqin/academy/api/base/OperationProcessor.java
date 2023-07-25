package com.tinqin.academy.api.base;

public interface OperationProcessor <R extends  OperationResponse, I extends OperationRequest>{
    R process(I input);
}
