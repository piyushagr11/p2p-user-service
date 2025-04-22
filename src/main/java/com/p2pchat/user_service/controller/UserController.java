package com.p2pchat.user_service.controller;

import com.p2pchat.user_service.dto.UserDto;
import com.p2pchat.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ GET /me - current user info
    @GetMapping("/me")
    public Mono<UserDto> getCurrentUser(Authentication authentication) {
        return userService.getByUsername(authentication.getName());
    }

    // ✅ GET /users?username=abc - search by username
    @GetMapping("/users")
    public Flux<UserDto> searchUsers(@RequestParam String username) {
        return userService.searchUsers(username);
    }

    @PostMapping("/contacts")
    public Mono<ResponseEntity<Void>> addContact(
            Authentication auth,
            @RequestParam String username
    ) {
        return userService.addContact(auth.getName(), username)
                .thenReturn(ResponseEntity.ok().build());
    }

    @GetMapping("/contacts")
    public Flux<UserDto> getContacts(Authentication auth) {
        return userService.getContacts(auth.getName());
    }
}