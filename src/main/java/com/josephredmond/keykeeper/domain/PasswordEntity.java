package com.josephredmond.keykeeper.domain;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PasswordEntity {
    private final UUID id = UUID.randomUUID();
    private String username;
    private char[] password;
    private String website;
    private String notes;
    private LocalDateTime creationDateTime = LocalDateTime.now();
    private LocalDateTime lastUpdatedDateTime = LocalDateTime.now();
}
