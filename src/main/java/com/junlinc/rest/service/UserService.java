package com.junlinc.rest.service;

import com.junlinc.rest.domain.Project;
import com.junlinc.rest.domain.Task;
import com.junlinc.rest.domain.User;
import com.junlinc.rest.repository.ProjectRepository;
import com.junlinc.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Update user details
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete user by ID
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    // List all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update last active time
    public User updateLastActive(String userId, LocalDateTime lastActive) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastActive(lastActive);
        return userRepository.save(user);
    }

    // Invite user
    public String inviteUser(String email, String projectId) {

        if (email != null && !email.isEmpty()) {
            return "User invited successfully to the board.";
        }
        return "User with the provided email not found.";
    }


}
