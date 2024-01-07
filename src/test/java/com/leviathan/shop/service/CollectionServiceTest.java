package com.leviathan.shop.service;

import com.leviathan.shop.dto.CollectionDto;
import com.leviathan.shop.entity.Collection;
import com.leviathan.shop.repository.CollectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class CollectionServiceTest {
    @Mock
    private CollectionRepository collectionRepository;

    @InjectMocks
    private CollectionService collectionService;

    private final Collection collection = Collection.builder()
            .id(1L)
            .title("Religion")
            .description("God is dead")
            .build();

    @Test
    public void getAllCollections() {
        Mockito.when(collectionRepository.findAll()).thenReturn(Collections.singletonList(collection));
        Assertions.assertEquals(collection, collectionService.getAll().get(0));
    }

    @Test
    public void getCollection() throws Exception {
        Mockito.when(collectionRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(collection));
        Assertions.assertEquals(collection, collectionService.get(1L));
    }

    @Test
    public void addCollection() {
        Mockito.when(collectionRepository.save(Mockito.any(Collection.class))).thenReturn(collection);
        Assertions.assertEquals(collection, collectionService.add(new CollectionDto()));
    }

    @Test
    public void updateCollection() {
        Mockito.when(collectionRepository.save(Mockito.any(Collection.class))).thenReturn(collection);
        Assertions.assertEquals(collection, collectionService.update(collection));
    }
}