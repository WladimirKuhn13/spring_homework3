package com.example.spring_homework3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserActivityController {
    private final static List<User> users = new ArrayList<>();

    @PostMapping
    public String addUser(@RequestBody User user) {
        users.add(user);
        return "Пользователь " + user + " добавлен в БД";
    }

    @GetMapping
    public String getUsers() {
        return "Все пользователи в базе данных " + users;
    }

    @GetMapping("/{userName}")
    public String getUserByUsername(@PathVariable("userName") String userName) {
        return "Пользолватель, которого вы искали " + users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .get();
    }

    @PutMapping("/{userName}")
    public String updatePost(@PathVariable("userName") String userName, @RequestBody String lastName) {
        users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .ifPresent(user -> user.setLastName(lastName));
        return "У пользователя " + userName + " изменили поле lastName на" + lastName;
    }

    @DeleteMapping("/{userName}")
    public String deleteUser(@PathVariable("userName") String userName) {
        users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .ifPresent(users::remove);

        return "Пользователь удален";
    }
}
