package com.tinqin.academy.api.codes.generate;

import com.tinqin.academy.api.base.OperationProcessor;

public interface GenerateCodeOperation extends OperationProcessor<GenerateCodeResponse, GenerateCodeRequest> {
    GenerateCodeResponse process(GenerateCodeRequest request);
}
