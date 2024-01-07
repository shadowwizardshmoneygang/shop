package com.leviathan.shop.service;

import com.leviathan.shop.dto.ClothesDto;
import com.leviathan.shop.entity.Clothes;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;
    private final CollectionService collectionService;
    private final CategoryService categoryService;

    public List<Clothes> getAll() {
        return clothesRepository.findAll();
    }

    public Clothes get(Long id) throws NotFoundException {
        return clothesRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Clothes with id '" + id + "' doesn't exist!")
        );
    }

    public List<Clothes> getAllByCollectionId(Long id) {
        return clothesRepository.findByCollectionId(id);
    }

    public List<Clothes> getAllByCategoryId(Long id) {
        return clothesRepository.findByCategoryId(id);
    }

    public Clothes add(ClothesDto clothesDto) throws NotFoundException {
        return clothesRepository.save(
                Clothes.builder()
                        .title(clothesDto.getTitle())
                        .description(clothesDto.getDescription())
                        .collection(collectionService.get(clothesDto.getCollectionId()))
                        .category(categoryService.get(clothesDto.getCategoryId()))
                        .amount(clothesDto.getAmount())
                        .price(clothesDto.getPrice())
                        .build()
        );
    }

    public Clothes update(Clothes clothes) {
        return clothesRepository.save(clothes);
    }

    public void delete(Long id) {
        clothesRepository.deleteById(id);
    }
}
