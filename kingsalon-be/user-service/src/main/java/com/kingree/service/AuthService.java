package com.kingree.service;

import com.kingree.payload.dto.SignupDTO;
import com.kingree.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email, String password) throws Exception;

    AuthResponse signup(SignupDTO signupDTO) throws Exception;

    AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception;
}
