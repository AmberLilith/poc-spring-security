package com.spring.security.repositories;

import com.spring.security.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserModelRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByName(String name);
}
