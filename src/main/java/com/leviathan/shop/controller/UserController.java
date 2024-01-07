package com.leviathan.shop.controller;

import com.leviathan.shop.dto.UserDto;
import com.leviathan.shop.entity.User;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "get all users")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "add user")
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.add(userDto), HttpStatus.OK);
    }

    @Operation(summary = "update user")
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @Operation(summary = "delete user")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return HttpStatus.OK;
    }
}
