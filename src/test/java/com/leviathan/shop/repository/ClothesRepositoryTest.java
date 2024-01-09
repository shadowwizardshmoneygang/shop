package com.leviathan.shop.repository;

import com.leviathan.shop.entity.Category;
import com.leviathan.shop.entity.Clothes;
import com.leviathan.shop.entity.Collection;
import com.leviathan.shop.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClothesRepositoryTest {
    @Mock
    private ClothesRepository clothesRepository;

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
    public void testFindAll() {
        Mockito.when(clothesRepository.findAll())
                .thenReturn(Collections.singletonList(clothes));
        Assertions.assertEquals(1, clothesRepository.findAll().size());
        Assertions.assertEquals(clothes, clothesRepository.findAll().get(0));
    }

    @Test
    public void testFind() throws Exception {
        Mockito.when(clothesRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clothes));
        Assertions.assertEquals(
                clothes, clothesRepository.findById(1L).orElseThrow(
                        () -> new NotFoundException("\"Clothes with id '\" + id + \"' doesn't exist!\"")
                )
        );
    }

    @Test
    public void testFindAllByCollectionId() {
        Mockito.when(clothesRepository.findByCollectionId(Mockito.anyLong()))
                .thenReturn(Collections.singletonList(clothes));
        Assertions.assertEquals(1, clothesRepository.findByCollectionId(1L).size());
    }

    @Test
    public void testFindAllByCategoryId() {
        Mockito.when(clothesRepository.findByCategoryId(Mockito.anyLong()))
                .thenReturn(Collections.singletonList(clothes));
        Assertions.assertEquals(1, clothesRepository.findByCategoryId(1L).size());
    }

    @Test
    public void testSaveAll() {
        Mockito.when(clothesRepository.saveAll(Mockito.anyCollection()))
                .thenReturn(Collections.singletonList(clothes));
        Assertions.assertEquals(1, clothesRepository.saveAll(Collections.singletonList(clothes)).size());
        Assertions.assertEquals(clothes, clothesRepository.saveAll(Collections.singletonList(clothes)).get(0));
    }
}
