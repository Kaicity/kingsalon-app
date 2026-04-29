package com.kingree.payload.dto;

import com.kingree.domain.UserRole;
import lombok.Data;

@Data
public class SignupDTO {
    private String fullName;
    private String password;
    private String username;
    private String email;
    private UserRole role;
}
