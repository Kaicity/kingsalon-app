package com.kingree.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.modal.User;
import com.kingree.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) throws Exception {
        User updateUser = userService.updatedUser(user, id);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwt(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
