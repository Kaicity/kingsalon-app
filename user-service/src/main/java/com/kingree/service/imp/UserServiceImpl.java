package com.kingree.service.imp;

import com.kingree.exception.UserException;
import com.kingree.modal.User;
import com.kingree.repository.UserRespository;
import com.kingree.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRespository userRespository;

    @Override
    public User createUser(User user) {
        return userRespository.save(user);
    }

    @Override
    public User updatedUser(User user, Long id) throws UserException {
        Optional<User> otp = userRespository.findById(id);
        if (otp.isEmpty()) {
            throw new UserException("User not found with id " + id);
        }
        User existingUser = otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setUsername(user.getUsername());

        return userRespository.save(existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRespository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> otp = userRespository.findById(id);
        if (otp.isPresent()) {
            return otp.get();
        }
        throw new UserException("user not found");
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User> otp = userRespository.findById(id);
        if (otp.isEmpty()) {
            throw new UserException("user not found with id " + id);
        }
        userRespository.deleteById(otp.get().getId());
    }
}
