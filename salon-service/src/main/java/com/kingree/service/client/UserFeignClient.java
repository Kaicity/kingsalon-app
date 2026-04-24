package com.kingree.service.client;

import com.kingree.payload.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping("/api/users/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) throws Exception;

    @GetMapping("/api/users/profile")
    ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception;
}
