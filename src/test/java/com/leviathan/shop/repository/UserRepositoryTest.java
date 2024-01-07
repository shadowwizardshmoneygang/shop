package com.leviathan.shop.repository;

import com.leviathan.shop.entity.User;
import com.leviathan.shop.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;

    private final User user = User.builder()
            .id(1L)
            .name("Anton")
            .phone("88005353535")
            .build();

    @Test
    public void findAll() {
        Mockito.when(userRepository.findAll())
                .thenReturn(Collections.singletonList(user));
        Assertions.assertEquals(1, userRepository.findAll().size());
        Assertions.assertEquals(user, userRepository.findAll().get(0));
    }

    @Test
    public void find() throws Exception {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        Assertions.assertEquals(
                user, userRepository.findById(1L).orElseThrow(
                        () -> new NotFoundException("\"User with id '\" + id + \"' doesn't exist!\"")
                )
        );
    }

    @Test
    public void saveAll() {
        Mockito.when(userRepository.saveAll(Mockito.anyCollection()))
                .thenReturn(Collections.singletonList(user));
        Assertions.assertEquals(1, userRepository.saveAll(Collections.singletonList(user)).size());
        Assertions.assertEquals(user, userRepository.saveAll(Collections.singletonList(user)).get(0));
    }
}
