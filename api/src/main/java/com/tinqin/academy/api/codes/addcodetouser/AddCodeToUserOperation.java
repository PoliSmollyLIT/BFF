package com.tinqin.academy.api.codes.addcodetouser;

import com.tinqin.academy.api.base.OperationProcessor;

public interface AddCodeToUserOperation extends OperationProcessor<AddCodeToUserResponse, AddCodeToUserRequest> {
    AddCodeToUserResponse process(AddCodeToUserRequest request);
}
