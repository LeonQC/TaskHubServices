package com.junlinc.rest.controller;

import com.junlinc.rest.domain.User;
import com.junlinc.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/API/v1")
public class UserController {

    @Autowired
    private UserService userService;

    // CRUD Operations
    @PostMapping("/User")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/User/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/User/id/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/User/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/Users")
    public ResponseEntity<Map<String, Object>> listAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(Map.of("status", "success", "data", users));
    }

    // Invite User
    @PostMapping("/invitations")
    public ResponseEntity<Map<String, String>> inviteUser(
            @RequestParam String email,
            @RequestParam String projectId) {
        String message = userService.inviteUser(email, projectId);
        String status = message.contains("successfully") ? "success" : "error";
        return ResponseEntity.ok(Map.of("status", status, "message", message));
    }

    // Update last active time
    @PatchMapping("/Users/{userId}")
    public ResponseEntity<Map<String, String>> updateLastActive(
            @PathVariable String userId,
            @RequestBody Map<String, String> request) {
        LocalDateTime lastActive = LocalDateTime.parse(request.get("last_active"));
        userService.updateLastActive(userId, lastActive);
        return ResponseEntity.ok(Map.of("status", "success", "msg", "Last active time updated successfully."));
    }

//    // User Detailed Name Card
//    @GetMapping("/Users/detail")
//    public ResponseEntity<Map<String, Object>> getUserDetailedCard(
//            @RequestParam String userId,
//            @RequestParam String fullName,
//            @RequestParam String emailAddress,
//            @RequestParam String publicName,
//            @RequestParam String initials,
//            @RequestParam String status,
//            @RequestParam String lastActive) {
//        User user = new User();
//        user.setId(userId);
//        user.setName(fullName);
//        user.setEmail(emailAddress);
//        user.setStatus(status);
//        user.setLastActive(LocalDateTime.parse(lastActive));
//        return ResponseEntity.ok(Map.of(
//                "_id", user.getId(),
//                "full_name", user.getName(),
//                "public_name", publicName,
//                "initials", initials,
//                "email", user.getEmail(),
//                "status", user.getStatus(),
//                "last_active", user.getLastActive().toString()
//        ));
//    }
}
