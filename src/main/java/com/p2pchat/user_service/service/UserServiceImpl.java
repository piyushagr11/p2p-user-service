package com.p2pchat.user_service.service;

import com.p2pchat.user_service.dto.UserDto;
import com.p2pchat.user_service.entity.Contact;
import com.p2pchat.user_service.entity.User;
import com.p2pchat.user_service.repository.ContactRepository;
import com.p2pchat.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Override
    public Mono<UserDto> getByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::toDto);
    }

    @Override
    public Flux<UserDto> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCase(query)
                .map(this::toDto);
    }

    @Override
    public Mono<Void> addContact(String owner, String contact) {
        if (owner.equals(contact)) {
            return Mono.error(new IllegalArgumentException("Cannot add yourself as contact"));
        }

        return contactRepository.existsByOwnerUsernameAndContactUsername(owner, contact)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Contact already added"));
                    }
                    return userRepository.findByUsername(contact)
                            .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                            .flatMap(found -> contactRepository.save(
                                    Contact.builder()
                                            .ownerUsername(owner)
                                            .contactUsername(contact)
                                            .build()
                            )).then();
                });
    }

    @Override
    public Flux<UserDto> getContacts(String owner) {
        return contactRepository.findAllByOwnerUsername(owner)
                .flatMap(contact -> userRepository.findByUsername(contact.getContactUsername()))
                .map(user -> new UserDto(user.getId(), user.getUsername()));
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }
}
