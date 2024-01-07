package com.leviathan.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leviathan.shop.dto.CategoryDto;
import com.leviathan.shop.entity.Category;
import com.leviathan.shop.service.CategoryService;
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
public class CategoryControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void initialize() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        objectMapper = new ObjectMapper();
    }

    private final Category category = Category.builder()
            .id(1L)
            .title("Shoes")
            .build();

    @Test
    public void getAllCategories() throws Exception {
        Mockito.when(categoryService.getAll()).thenReturn(Collections.singletonList(category));
        mockMvc.perform(MockMvcRequestBuilders.get("/category/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(category.getId()));
    }

    @Test
    public void getCategory() throws Exception {
        Mockito.when(categoryService.get(1L)).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.get("/category/{id}", category.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(category.getId()));
    }

    @Test
    public void addCategory() throws Exception {
        Mockito.when(categoryService.add(Mockito.any(CategoryDto.class))).thenReturn(category);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/category/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(category))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(category.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(category.getTitle()));
    }

    @Test
    public void updateCategory() throws Exception {
        Mockito.when(categoryService.update(Mockito.any(Category.class))).thenReturn(category);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/category/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(category))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(category.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(category.getTitle()));
    }

    @Test
    public void deleteCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/category/delete/{id}", category.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
