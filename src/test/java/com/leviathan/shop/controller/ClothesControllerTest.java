package com.leviathan.shop.controller;

import com.leviathan.shop.entity.Category;
import com.leviathan.shop.entity.Clothes;
import com.leviathan.shop.entity.Collection;
import com.leviathan.shop.service.ClothesService;
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

import java.math.BigDecimal;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class ClothesControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ClothesService clothesService;

    @InjectMocks
    private ClothesController clothesController;

    @BeforeEach
    public void initialize() {
        mockMvc = MockMvcBuilders.standaloneSetup(clothesController).build();
    }

    private final Clothes clothes = Clothes.builder()
            .id(1L)
            .title("Magnolia")
            .description("Suckless shoes")
            .collection(
                    Collection.builder()
                            .id(1L)
                            .title("Religion")
                            .description("God is dead")
                            .build()
            )
            .category(
                    Category.builder()
                            .id(1L)
                            .title("Shoes")
                            .build()
            )
            .amount(100)
            .price(BigDecimal.valueOf(100))
            .build();

    @Test
    public void getAllClothes() throws Exception {
        Mockito.when(clothesService.getAll()).thenReturn(Collections.singletonList(clothes));
        mockMvc.perform(MockMvcRequestBuilders.get("/clothes/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(clothes.getId()));
    }

    @Test
    public void getClothes() throws Exception {
        Mockito.when(clothesService.get(1L)).thenReturn(clothes);
        mockMvc.perform(MockMvcRequestBuilders.get("/clothes/{id}", clothes.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clothes.getId()));
    }

    @Test
    public void getClothesByCollectionId() throws Exception {
        Mockito.when(clothesService.getAllByCollectionId(1L)).thenReturn(Collections.singletonList(clothes));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/clothes/collection/{id}", clothes.getCollection().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(clothes.getCollection().getId()));
    }

    @Test
    public void getClothesByCategoryId() throws Exception {
        Mockito.when(clothesService.getAllByCategoryId(1L)).thenReturn(Collections.singletonList(clothes));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/clothes/category/{id}", clothes.getCollection().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(clothes.getCollection().getId()));
    }

    @Test
    public void deleteClothes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clothes/delete/{id}", clothes.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
