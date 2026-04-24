package com.kingree.payload.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
//    private String password;
    private String username;
    private String email;
    private boolean enabled;
    private List<Credential> credentials = new ArrayList<>();
}
