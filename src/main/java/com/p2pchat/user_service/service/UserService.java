package com.p2pchat.user_service.service;

import com.p2pchat.user_service.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDto> getByUsername(String username);
    Flux<UserDto> searchUsers(String query);

    public Mono<Void> addContact(String owner, String contact);
    public Flux<UserDto> getContacts(String owner);
}
