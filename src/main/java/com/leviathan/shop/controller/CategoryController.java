package com.leviathan.shop.controller;

import com.leviathan.shop.dto.CategoryDto;
import com.leviathan.shop.entity.Category;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category")
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "get all categories")
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "get category by id")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(categoryService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "add category")
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.add(categoryDto), HttpStatus.OK);
    }

    @Operation(summary = "update category")
    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category) {
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }

    @Operation(summary = "delete category by id")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return HttpStatus.OK;
    }
}
