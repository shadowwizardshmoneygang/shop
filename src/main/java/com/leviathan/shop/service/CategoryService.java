package com.leviathan.shop.service;

import com.leviathan.shop.dto.CategoryDto;
import com.leviathan.shop.entity.Category;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category get(Long id) throws NotFoundException {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category with id '" + id + "' doesn't exist!")
        );
    }

    public Category add(CategoryDto categoryDto) {
        return categoryRepository.save(
                Category.builder()
                        .title(categoryDto.getTitle())
                        .build()
        );
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
