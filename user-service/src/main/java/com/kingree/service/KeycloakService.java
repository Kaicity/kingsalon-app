package com.kingree.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kingree.payload.dto.Credential;
import com.kingree.payload.dto.KeycloakRole;
import com.kingree.payload.dto.KeycloakUserDTO;
import com.kingree.payload.dto.SignupDTO;
import com.kingree.payload.dto.UserRequest;
import com.kingree.payload.response.TokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private static final String KEYCLOAK_BASE_URL = "http://localhost:8080";
    private static final String KEYCLOAK_ADMIN_API = KEYCLOAK_BASE_URL + "/admin/realms/master/users";

    private static final String TOKEN_URL = KEYCLOAK_BASE_URL + "/realms/master/protocol/openid-connect/token";

    private static final String CLIENT_ID = "salon-booking-client";
    private static final String CLIENT_SECRET = "0uBARqUlNhaArv6P7JqDkYO6ikRw8iDQ";
    private static final String GRANT_TYPE = "password";
    private static final String scope = "openid email profile";
    private static final String username = "kaicity";
    private static final String password = "123456";
    private static final String clientId = "53ecc2b5-b280-4897-b991-13cecc86b71c";

    private final RestTemplate restTemplate;

    // Call Api KeyCloak
    public void createUser(SignupDTO signupDTO) throws Exception {
        // Get access token valid
        String ACCESS_TOKEN = getAdminAccessToken(username, password, GRANT_TYPE, null).getAccessToken();

        Credential credential = new Credential();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(signupDTO.getPassword());

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(signupDTO.getUsername());
        userRequest.setEmail(signupDTO.getEmail());
        userRequest.setEnabled(true);
        String fullName = signupDTO.getFullName().trim();
        String[] parts = fullName.split("\\s+");

        String firstName = parts[parts.length - 1];
        String lastName = "";

        if (parts.length > 1) {
            lastName = String.join(" ", Arrays.copyOf(parts, parts.length - 1));
        }

        userRequest.setFirstName(firstName);
        userRequest.setLastName(lastName);
        userRequest.getCredentials().add(credential);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(ACCESS_TOKEN);

        HttpEntity<UserRequest> requestEntity = new HttpEntity<>(userRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KEYCLOAK_ADMIN_API,
                HttpMethod.POST,
                requestEntity,
                String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("User created successfully");

            KeycloakUserDTO user = fetchFirstUserByUsername(signupDTO.getUsername(), ACCESS_TOKEN);

            KeycloakRole role = getRoleByName(clientId, ACCESS_TOKEN, signupDTO.getRole().toString());

            List<KeycloakRole> roles = new ArrayList<>();
            roles.add(role);

            assignRoleToUser(user.getId(), clientId, roles, ACCESS_TOKEN);
        } else {
            System.out.println("User creation failed");
            throw new Exception(response.getBody());
        }
    }

    public TokenResponse getAdminAccessToken(String username,
            String password,
            String grantType,
            String refreshToken) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

        requestBody.add("client_id", CLIENT_ID);

        // Nếu là confidential thì bật, không thì comment
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("scope", scope);

        if ("password".equals(grantType)) {
            requestBody.add("grant_type", "password");
            requestBody.add("username", username);
            requestBody.add("password", password);
        } else if ("refresh_token".equals(grantType)) {
            requestBody.add("grant_type", "refresh_token");
            requestBody.add("refresh_token", refreshToken);
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        }

        throw new Exception("Failed to obtain access token");
    }

    public KeycloakRole getRoleByName(String clientId, String token, String role) {
        String url = KEYCLOAK_BASE_URL
                + "/admin/realms/master/clients/"
                + clientId
                + "/roles/"
                + role;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeycloakRole> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                KeycloakRole.class);

        return response.getBody();
    }

    public KeycloakUserDTO fetchFirstUserByUsername(String username, String token) throws Exception {
        String url = KEYCLOAK_BASE_URL + "/admin/realms/master/users?username=" + username;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeycloakUserDTO[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                KeycloakUserDTO[].class);

        KeycloakUserDTO[] users = response.getBody();

        if (users != null && users.length > 0) {
            return response.getBody()[0];
        }

        throw new Exception("User not found with username " + username);
    }

    public void assignRoleToUser(String userId, String clientId, List<KeycloakRole> roles, String token)
            throws Exception {
        String url = KEYCLOAK_BASE_URL + "/admin/realms/master/users/"
                + userId +
                "/role-mappings/clients/"
                + clientId;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<KeycloakRole>> requestEntity = new HttpEntity<>(roles, headers);

        try {
            ResponseEntity<KeycloakRole> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    KeycloakRole.class);
        } catch (Exception e) {
            throw new Exception("Failed to assign new role " + e.getMessage());
        }
    }

    public KeycloakUserDTO getUserProfileByJwt(String token) throws Exception {
        String url = KEYCLOAK_BASE_URL + "/realms/master/protocol/openid-connect/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<KeycloakUserDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    KeycloakUserDTO.class);

            return response.getBody();
        } catch (Exception e) {
            throw new Exception("Failed to get user info " + e.getMessage());
        }
    }
}
