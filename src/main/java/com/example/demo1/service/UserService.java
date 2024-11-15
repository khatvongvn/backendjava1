package com.example.demo1.service;
import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo1.utils.PasswordUtils;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }
        // Hash mật khẩu trước khi lưu
        String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
}

