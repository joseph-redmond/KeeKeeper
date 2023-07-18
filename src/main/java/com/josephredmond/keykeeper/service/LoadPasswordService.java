package com.josephredmond.keykeeper.service;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadPasswordService {
    private char[] password = new char[]{};

    public void wipe() {
        for (int i = 0; i < password.length; i++) {
            password[i] = '0';
        }
        password = new char[]{};
    }
}
