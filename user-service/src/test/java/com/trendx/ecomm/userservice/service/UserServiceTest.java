package com.trendx.ecomm.userservice.service;

import com.trendx.ecomm.userservice.entity.User;
import com.trendx.ecomm.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    void findById() {
        //Given
        User user=new User("Talha Toprak", "t@t.com","web");
        userRepository.save(user);

        //When
        User user1 = userRepository.findById(user.getId()).orElse(null);

        //Then
        assertEquals("Talha Toprak", user1.getFullName());
    }

    @Test
    void createUser() {
        //Given
        User user = new User("Yavuz Bilgin", "y2@y.com","mobil");

        //When
        User savedUser = userService.createUser(user);
        User foundUser = userRepository.findById(savedUser.getId()).orElseThrow();

        //Then
        assertEquals(savedUser.getEmail(), foundUser.getEmail());
    }

    @Test
    void deleteById() {
        //Given
        User user = new User("Ahmet Bilgin", "a@b.com","mobil");
        User savedUser = userRepository.save(user);
        Long savedUserId = savedUser.getId();

        //When
        userService.deleteById(savedUserId);

        //Then
        Optional<User> foundUser = userRepository.findById(savedUserId);
        assertTrue(foundUser.isEmpty());
    }
    @Test
    void deleteById2() {
        userService.deleteById(6L);
    }

    @Test
    void updateUser() {
        //Given
        User user = new User("Ayse Oz", "a@o.com","web");
        User savedUser = userRepository.save(user);

        //When
        String newEmail = "new@mail.com";
        savedUser.setEmail(newEmail);
        User updatedUser = userService.updateUser(savedUser);

        //Then
        assertEquals(newEmail, updatedUser.getEmail());
    }

}
