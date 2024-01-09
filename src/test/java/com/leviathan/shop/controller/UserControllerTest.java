package com.leviathan.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leviathan.shop.dto.UserDto;
import com.leviathan.shop.entity.User;
import com.leviathan.shop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void initialize() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    private final User user = User.builder()
            .id(1L)
            .name("Anton")
            .phone("88005353535")
            .build();

    @Test
    public void testGetAllUsers() throws Exception {
        Mockito.when(userService.getAll()).thenReturn(Collections.singletonList(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(user.getId()));
    }

    @Test
    public void testGetUser() throws Exception {
        Mockito.when(userService.get(1L)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()));
    }

    @Test
    public void testAddUser() throws Exception {
        Mockito.when(userService.add(Mockito.any(UserDto.class))).thenReturn(user);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(user.getPhone()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Mockito.when(userService.update(Mockito.any(User.class))).thenReturn(user);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/user/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(user.getPhone()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{id}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

