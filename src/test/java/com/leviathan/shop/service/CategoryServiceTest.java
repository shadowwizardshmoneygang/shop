package com.leviathan.shop.service;

import com.leviathan.shop.dto.CategoryDto;
import com.leviathan.shop.entity.Category;
import com.leviathan.shop.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private final Category category = Category.builder()
            .id(1L)
            .title("Shoes")
            .build();

    @Test
    public void getAllCategories() {
        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        Assertions.assertEquals(category, categoryService.getAll().get(0));
    }

    @Test
    public void getCategory() throws Exception {
        Mockito.when(categoryRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(category));
        Assertions.assertEquals(category, categoryService.get(1L));
    }

    @Test
    public void addCategory() {
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        Assertions.assertEquals(category, categoryService.add(new CategoryDto()));
    }

    @Test
    public void updateCategory() {
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        Assertions.assertEquals(category, categoryService.update(category));
    }
}
