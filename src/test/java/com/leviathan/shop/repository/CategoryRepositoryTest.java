package com.leviathan.shop.repository;

import com.leviathan.shop.entity.Category;
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
public class CategoryRepositoryTest {
    @Mock
    private CategoryRepository categoryRepository;

    private final Category category = Category.builder()
            .id(1L)
            .title("Shoes")
            .build();

    @Test
    public void testFindAll() {
        Mockito.when(categoryRepository.findAll())
                .thenReturn(Collections.singletonList(category));
        Assertions.assertEquals(1, categoryRepository.findAll().size());
        Assertions.assertEquals(category, categoryRepository.findAll().get(0));
    }

    @Test
    public void testFind() throws Exception {
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(category));
        Assertions.assertEquals(
                category, categoryRepository.findById(1L).orElseThrow(
                        () -> new NotFoundException("\"Category with id '\" + id + \"' doesn't exist!\"")
                )
        );
    }

    @Test
    public void testSaveAll() {
        Mockito.when(categoryRepository.saveAll(Mockito.anyCollection()))
                .thenReturn(Collections.singletonList(category));
        Assertions.assertEquals(1, categoryRepository.saveAll(Collections.singletonList(category)).size());
        Assertions.assertEquals(category, categoryRepository.saveAll(Collections.singletonList(category)).get(0));
    }
}
