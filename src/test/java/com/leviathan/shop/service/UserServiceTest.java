package com.leviathan.shop.service;

import com.leviathan.shop.dto.UserDto;
import com.leviathan.shop.entity.User;
import com.leviathan.shop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final User user = User.builder()
            .id(1L)
            .name("Anton")
            .phone("88005353535")
            .build();

    @Test
    public void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        Assertions.assertEquals(user, userService.getAll().get(0));
    }

    @Test
    public void getUser() throws Exception {
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(user));
        Assertions.assertEquals(user, userService.get(1L));
    }

    @Test
    public void addUser() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Assertions.assertEquals(user, userService.add(new UserDto()));
    }

    @Test
    public void updateUser() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Assertions.assertEquals(user, userService.update(user));
    }
}
