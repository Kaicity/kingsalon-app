package com.kingree.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingree.exception.UserException;
import com.kingree.modal.User;
import com.kingree.payload.dto.KeycloakUserDTO;
import com.kingree.repository.UserRepository;
import com.kingree.service.KeycloakService;
import com.kingree.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final KeycloakService keycloakService;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updatedUser(User user, Long id) throws UserException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new UserException("User not found with id " + id);
        }
        User existingUser = otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setUsername(user.getUsername());

        return userRepository.save(existingUser);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isPresent()) {
            return otp.get();
        }
        throw new UserException("user not found");
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new UserException("user not found with id " + id);
        }
        userRepository.deleteById(otp.get().getId());
    }

    @Override
    public User getUserFromJwt(String jwt) throws Exception {
        KeycloakUserDTO keycloakUserDTO = keycloakService.getUserProfileByJwt(jwt);
        User user = userRepository.findByEmail(keycloakUserDTO.getEmail());
        if (user == null) {
            throw new UserException("User not found from jwt...");
        }
        return user;
    }
}
