package com.scaler.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid",columnDefinition = "binary(16)",updatable = false, nullable = false)
    public UUID uuid;
}
