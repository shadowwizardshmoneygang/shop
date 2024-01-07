package com.leviathan.shop.service;

import com.leviathan.shop.dto.UserDto;
import com.leviathan.shop.entity.User;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id '" + id + "' doesn't exist!")
        );
    }

    public User add(UserDto userDto) {
        return userRepository.save(
                User.builder()
                        .name(userDto.getName())
                        .phone(userDto.getPhone())
                        .build()
        );
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
