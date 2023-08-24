package com.tinqin.academy.persistence.repositories;

import com.tinqin.academy.persistence.models.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCodesRepository extends JpaRepository<UserCode, UUID> {
    Optional<UserCode> findUserCodeByUser(UUID user);
}
