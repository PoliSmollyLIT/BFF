package com.tinqin.academy.api.codes.use;

import com.tinqin.academy.api.base.OperationProcessor;

public interface UserCodeOperation extends OperationProcessor<UserCodeResponse, UserCodeRequest> {
    UserCodeResponse process(UserCodeRequest request);
}
