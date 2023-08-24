package com.tinqin.academy.core.codes;

import com.tinqin.academy.api.codes.delete.DeleteCodeOperation;
import com.tinqin.academy.api.codes.delete.DeleteCodeRequest;
import com.tinqin.academy.api.codes.delete.DeleteCodeResponse;
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
public class DeleteCodeCore implements DeleteCodeOperation {
    private final UserCodesRepository userCodesRepository;
    private final UserRepository userRepository;

    @Override
    public DeleteCodeResponse process(DeleteCodeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User with this ID not found"));
        UserCode userCode = userCodesRepository.findUserCodeByUser(user.getId())
                .orElseThrow(()-> new EntityNotFoundException("UserCode with this ID not found"));
        DeleteCodeResponse response = DeleteCodeResponse.builder()
                .discount(0)
                .build();
        if(userCode.getExpiration().after(Timestamp.valueOf(LocalDateTime.now()))){
            response.setDiscount(15);
        }
        userCodesRepository.delete(userCode);
        return response;
    }
}
