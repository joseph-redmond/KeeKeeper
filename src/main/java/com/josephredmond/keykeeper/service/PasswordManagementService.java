package com.josephredmond.keykeeper.service;

import com.josephredmond.keykeeper.domain.PasswordEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class PasswordManagementService {

    private final List<PasswordEntity> passwordEntities = new ArrayList<>();

    public Optional getPasswordEntityById(UUID id) {
        try {
            PasswordEntity passwordEntity = passwordEntities.stream().filter(i -> id.equals(i.getId())).findFirst().orElseThrow();
            return Optional.of(passwordEntity);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<PasswordEntity> getAllPasswordEntities() {
        return this.passwordEntities;
    }

    public void savePasswordEntity(PasswordEntity passwordEntity) {
        Optional<PasswordEntity> maybePreviouslySavedPasswordEntity = getPasswordEntityById(passwordEntity.getId());
        if (maybePreviouslySavedPasswordEntity.isEmpty()) {
            passwordEntities.add(passwordEntity);
            return;
        }
        PasswordEntity previouslySavedPasswordEntity = maybePreviouslySavedPasswordEntity.get();

        if(passwordEntity.getPassword().length > 0) {
            previouslySavedPasswordEntity.setPassword(passwordEntity.getPassword());
        }

        if(passwordEntity.getUsername() != null) {
            previouslySavedPasswordEntity.setUsername(passwordEntity.getUsername());
        }

        if(passwordEntity.getWebsite() != null) {
            previouslySavedPasswordEntity.setWebsite(passwordEntity.getWebsite());
        }

        if(passwordEntity.getNotes() != null) {
            previouslySavedPasswordEntity.setNotes(passwordEntity.getNotes());
        }

        previouslySavedPasswordEntity.setLastUpdatedDateTime(LocalDateTime.now());
    }

    public Optional<PasswordEntity> removePasswordEntity(UUID id) {
        Optional<PasswordEntity> passwordEntity = getPasswordEntityById(id);
        if (passwordEntity.isEmpty()) {
            return Optional.empty();
        }
        passwordEntities.remove(passwordEntity.get());
        return passwordEntity;
    }
}
