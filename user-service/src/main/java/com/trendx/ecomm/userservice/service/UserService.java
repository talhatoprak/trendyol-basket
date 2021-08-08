package com.trendx.ecomm.userservice.service;

import com.trendx.ecomm.userservice.entity.User;
import com.trendx.ecomm.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KafkaService kafkaService;

    public UserService(UserRepository userRepository, KafkaService kafkaService) {
        this.userRepository = userRepository;
        this.kafkaService = kafkaService;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(
            String.format("Entity with id: %s does not exists.", id)
        ));
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email has already taken.");
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
        kafkaService.sendMessage(id.toString(),"deleteUser");
    }

    public User updateUser(User user) {
        Long userId = user.getId();
        this.findById(userId);
        //Todo: test edilecek.
        return userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
