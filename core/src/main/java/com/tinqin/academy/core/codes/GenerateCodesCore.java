package com.tinqin.academy.core.codes;

import com.tinqin.academy.api.codes.generate.GenerateCodeOperation;
import com.tinqin.academy.api.codes.generate.GenerateCodeRequest;
import com.tinqin.academy.api.codes.generate.GenerateCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.text.RandomStringGenerator;

@Service
@RequiredArgsConstructor
public class GenerateCodesCore implements GenerateCodeOperation {

    @Override
    public GenerateCodeResponse process(GenerateCodeRequest request) {
        char[][] allowedCharacterRanges = {{'a','z'},{'A','Z'},{'0','9'}};

        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange(allowedCharacterRanges)
                .build();
        GenerateCodeResponse response = GenerateCodeResponse.builder()
                .code(generator.generate(6))
                .build();
        return response;
    }
}
