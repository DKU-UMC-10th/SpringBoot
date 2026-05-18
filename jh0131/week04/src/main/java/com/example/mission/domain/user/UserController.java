package com.example.mission.domain.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for user operations.  Provides endpoints to list users,
 * retrieve a specific user and register a new user.  Passwords are
 * transmitted in clear text here solely for demonstration; in real
 * applications always store and transmit passwords securely.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserDto dto) {
        User created = userService.registerUser(
                dto.getEmail(),
                dto.getPassword(),
                dto.getNickname(),
                dto.getPhone()
        );
        return ResponseEntity.ok(created);
    }

    /**
     * DTO for user registration.  Separate from the entity to decouple
     * persistence details from the API contract.  In production you should
     * perform validation on these fields.
     */
    public static class UserDto {
        private String email;
        private String password;
        private String nickname;
        private String phone;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}