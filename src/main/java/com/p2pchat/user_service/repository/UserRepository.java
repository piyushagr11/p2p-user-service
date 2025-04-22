package com.p2pchat.user_service.repository;

import com.p2pchat.user_service.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
    Flux<User> findByUsernameContainingIgnoreCase(String partial);
}
