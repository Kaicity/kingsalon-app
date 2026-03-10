package com.kingree.service;

import com.kingree.exception.UserException;
import com.kingree.modal.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User updatedUser(User user, Long id) throws UserException;

    List<User> getAllUsers ();

    User getUserById (Long id) throws UserException;

    void deleteUser(Long id) throws UserException;
}
