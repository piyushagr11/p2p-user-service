package com.p2pchat.user_service.repository;

import com.p2pchat.user_service.entity.Contact;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContactRepository extends ReactiveMongoRepository<Contact, String> {
    Flux<Contact> findAllByOwnerUsername(String ownerUsername);
    Mono<Boolean> existsByOwnerUsernameAndContactUsername(String owner, String contact);
}
