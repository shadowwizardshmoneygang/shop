package com.leviathan.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leviathan.shop.dto.CollectionDto;
import com.leviathan.shop.entity.Collection;
import com.leviathan.shop.service.CollectionService;
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
public class CollectionControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private CollectionService collectionService;

    @InjectMocks
    private CollectionController collectionController;

    @BeforeEach
    public void initialize() {
        mockMvc = MockMvcBuilders.standaloneSetup(collectionController).build();
        objectMapper = new ObjectMapper();
    }

    private final Collection collection = Collection.builder()
            .id(1L)
            .title("Religion")
            .description("God is dead")
            .build();

    @Test
    public void getAllCollections() throws Exception {
        Mockito.when(collectionService.getAll()).thenReturn(Collections.singletonList(collection));
        mockMvc.perform(MockMvcRequestBuilders.get("/collection/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(collection.getId()));
    }

    @Test
    public void getCollection() throws Exception {
        Mockito.when(collectionService.get(1L)).thenReturn(collection);
        mockMvc.perform(MockMvcRequestBuilders.get("/collection/{id}", collection.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(collection.getId()));
    }

    @Test
    public void addCollection() throws Exception {
        Mockito.when(collectionService.add(Mockito.any(CollectionDto.class))).thenReturn(collection);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/collection/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(collection))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(collection.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(collection.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(collection.getDescription()));
    }

    @Test
    public void updateCollection() throws Exception {
        Mockito.when(collectionService.update(Mockito.any(Collection.class))).thenReturn(collection);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/collection/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(collection))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(collection.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(collection.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(collection.getDescription()));
    }

    @Test
    public void deleteCollection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/collection/delete/{id}", collection.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
