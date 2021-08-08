package com.trendx.ecomm.userservice.controller;

import com.trendx.ecomm.userservice.entity.User;
import com.trendx.ecomm.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user/")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
        System.out.printf("UserController :: findById :: UserId = %s%n\n", id);
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.printf("UserController :: createUser :: %s\n", user);
        return userService.createUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        System.out.printf("UserController :: deleteById :: UserId = %s%n", id);
        userService.deleteById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        System.out.printf("UserController :: updateUser :: User = %s%n", user);
        return userService.updateUser(user);
    }
}
