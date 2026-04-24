package com.kingree.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kingree.exception.UserException;
import com.kingree.modal.User;

public interface UserService {
    User createUser(User user);

    User updatedUser(User user, Long id) throws UserException;

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long id) throws UserException;

    void deleteUser(Long id) throws UserException;

    User getUserFromJwt(String jwt) throws Exception;
}
