package com.romm.timemanagement.DTO;

import com.romm.timemanagement.enums.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
    
}
