package com.tinqin.academy.core.codes;

import com.tinqin.academy.api.codes.addcodetouser.AddCodeToUserOperation;
import com.tinqin.academy.api.codes.addcodetouser.AddCodeToUserRequest;
import com.tinqin.academy.api.codes.addcodetouser.AddCodeToUserResponse;
import com.tinqin.academy.api.codes.delete.DeleteCodeOperation;
import com.tinqin.academy.api.codes.delete.DeleteCodeRequest;
import com.tinqin.academy.api.codes.delete.DeleteCodeResponse;
import com.tinqin.academy.api.codes.generate.GenerateCodeOperation;
import com.tinqin.academy.api.codes.generate.GenerateCodeRequest;
import com.tinqin.academy.api.codes.generate.GenerateCodeResponse;
import com.tinqin.academy.api.codes.use.UserCodeOperation;
import com.tinqin.academy.api.codes.use.UserCodeRequest;
import com.tinqin.academy.api.codes.use.UserCodeResponse;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.models.UserCode;
import com.tinqin.academy.persistence.repositories.UserCodesRepository;
import com.tinqin.academy.persistence.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UseCodeCore implements UserCodeOperation {
    private final UserCodesRepository userCodesRepository;
    private final UserRepository userRepository;
    private final AddCodeToUserOperation addCodeToUser;
    private final DeleteCodeOperation deleteCode;
    private final GenerateCodeOperation generateCode;

    @Override
    public UserCodeResponse process(UserCodeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User with this ID not found"));
        Optional<UserCode> userCode = userCodesRepository.findUserCodeByUser(user.getId());
        UserCodeResponse response = UserCodeResponse.builder()
                .discount(0)
                .build();
        if(userCode.isEmpty()){
            AddCodeToUserResponse addCode = addCodeToUser.process(AddCodeToUserRequest.builder().userId(user.getId()).build());
        }
        else{
            DeleteCodeResponse deletedCode = deleteCode.process(DeleteCodeRequest.builder().userId(user.getId()).build());
            response.setDiscount(deletedCode.getDiscount());
        }
        return response;
    }
}
