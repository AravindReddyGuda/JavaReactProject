package com.sb.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.sb.demo.model.User;

@RestController
public class userController {

    private List<User> users = new ArrayList<>();

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return users;
    }

    // Get user by ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    // Create a new user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }
}

