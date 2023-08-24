package com.tinqin.academy.api.codes.delete;

import com.tinqin.academy.api.base.OperationProcessor;

public interface DeleteCodeOperation extends OperationProcessor<DeleteCodeResponse, DeleteCodeRequest> {
    DeleteCodeResponse process(DeleteCodeRequest request);
}
