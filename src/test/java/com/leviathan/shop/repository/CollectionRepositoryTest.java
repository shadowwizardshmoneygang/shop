package com.leviathan.shop.repository;

import com.leviathan.shop.entity.Collection;
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
public class CollectionRepositoryTest {
    @Mock
    private CollectionRepository collectionRepository;

    private final Collection collection = Collection.builder()
            .id(1L)
            .title("Religion")
            .description("God is dead")
            .build();

    @Test
    public void findAll() {
        Mockito.when(collectionRepository.findAll())
                .thenReturn(Collections.singletonList(collection));
        Assertions.assertEquals(1, collectionRepository.findAll().size());
        Assertions.assertEquals(collection, collectionRepository.findAll().get(0));
    }

    @Test
    public void find() throws Exception {
        Mockito.when(collectionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(collection));
        Assertions.assertEquals(
                collection, collectionRepository.findById(1L).orElseThrow(
                        () -> new NotFoundException("\"Collection with id '\" + id + \"' doesn't exist!\"")
                )
        );
    }

    @Test
    public void saveAll() {
        Mockito.when(collectionRepository.saveAll(Mockito.anyCollection()))
                .thenReturn(Collections.singletonList(collection));
        Assertions.assertEquals(1, collectionRepository.saveAll(Collections.singletonList(collection)).size());
        Assertions.assertEquals(collection, collectionRepository.saveAll(Collections.singletonList(collection)).get(0));
    }
}
