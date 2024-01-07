package com.leviathan.shop.service;

import com.leviathan.shop.dto.ClothesDto;
import com.leviathan.shop.entity.Category;
import com.leviathan.shop.entity.Clothes;
import com.leviathan.shop.entity.Collection;
import com.leviathan.shop.repository.ClothesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class ClothesServiceTest {
    @Mock
    private ClothesRepository clothesRepository;

    @Mock
    private CollectionService collectionService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ClothesService clothesService;

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
    public void getAllCategories() {
        Mockito.when(clothesRepository.findAll()).thenReturn(Collections.singletonList(clothes));
        Assertions.assertEquals(clothes, clothesService.getAll().get(0));
    }

    @Test
    public void getCategory() throws Exception {
        Mockito.when(clothesRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(clothes));
        Assertions.assertEquals(clothes, clothesService.get(1L));
    }

    @Test
    public void getClothesByCollectionId() {
        Mockito.when(clothesRepository.findByCollectionId(Mockito.any(Long.class)))
                .thenReturn(Collections.singletonList(clothes));
        Assertions.assertEquals(clothes, clothesService.getAllByCollectionId(1L).get(0));
    }

    @Test
    public void getClothesByCategoryId() {
        Mockito.when(clothesRepository.findByCategoryId(Mockito.any(Long.class)))
                .thenReturn(Collections.singletonList(clothes));
        Assertions.assertEquals(clothes, clothesService.getAllByCategoryId(1L).get(0));
    }

    @Test
    public void addClothes() throws Exception {
        Mockito.when(clothesRepository.save(Mockito.any(Clothes.class))).thenReturn(clothes);
        Assertions.assertEquals(clothes, clothesService.add(new ClothesDto()));
    }

    @Test
    public void updateClothes() {
        Mockito.when(clothesRepository.save(Mockito.any(Clothes.class))).thenReturn(clothes);
        Assertions.assertEquals(clothes, clothesService.update(clothes));
    }
}
