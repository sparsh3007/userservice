package com.scaler.userservice.repositeries;

import com.scaler.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    Optional<Session> findByTokenAndUser_Uuid(String token, UUID userId);
}
