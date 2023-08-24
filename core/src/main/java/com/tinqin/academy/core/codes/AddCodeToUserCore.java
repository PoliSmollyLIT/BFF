package com.tinqin.academy.core.codes;

import com.tinqin.academy.api.codes.addcodetouser.AddCodeToUserOperation;
import com.tinqin.academy.api.codes.addcodetouser.AddCodeToUserRequest;
import com.tinqin.academy.api.codes.addcodetouser.AddCodeToUserResponse;
import com.tinqin.academy.api.codes.generate.GenerateCodeOperation;
import com.tinqin.academy.api.codes.generate.GenerateCodeRequest;
import com.tinqin.academy.api.codes.generate.GenerateCodeResponse;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.models.UserCode;
import com.tinqin.academy.persistence.repositories.UserCodesRepository;
import com.tinqin.academy.persistence.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddCodeToUserCore implements AddCodeToUserOperation {
    private final UserRepository userRepository;
    private final UserCodesRepository userCodesRepository;
    private final GenerateCodeOperation generateCode;

    @Override
    public AddCodeToUserResponse process(AddCodeToUserRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User with this ID not found"));
        GenerateCodeResponse code = generateCode.process(new GenerateCodeRequest());
        Timestamp expiration = Timestamp.valueOf(LocalDateTime.now().plusDays(30));

        UserCode userCode = UserCode.builder()
                .user(user.getId())
                .code(code.getCode())
                .expiration(expiration)
                .build();
        userCodesRepository.save(userCode);

        return AddCodeToUserResponse.builder()
                .id(userCode.getId())
                .userId(userCode.getUser())
                .code(code.getCode())
                .expiration(expiration)
                .build();
    }
}

