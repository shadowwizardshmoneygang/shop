package com.leviathan.shop.controller;

import com.leviathan.shop.dto.ClothesDto;
import com.leviathan.shop.entity.Clothes;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.service.ClothesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clothes")
@RestController
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesController {
    private final ClothesService clothesService;

    @Operation(summary = "get all clothes")
    @GetMapping("/all")
    public ResponseEntity<List<Clothes>> getAllClothes() {
        return new ResponseEntity<>(clothesService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "get clothes by id")
    @GetMapping("/{id}")
    public ResponseEntity<Clothes> getClothesById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(clothesService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "get clothes by collection id")
    @GetMapping("/collection/{id}")
    public ResponseEntity<List<Clothes>> getAllClothesByCollectionId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clothesService.getAllByCollectionId(id), HttpStatus.OK);
    }

    @Operation(summary = "get clothes by category id")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Clothes>> getAllClothesByCategoryId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clothesService.getAllByCategoryId(id), HttpStatus.OK);
    }

    @Operation(summary = "add clothes")
    @PostMapping("/add")
    public ResponseEntity<Clothes> addClothes(@RequestBody @Valid ClothesDto clothesDto) throws NotFoundException {
        return new ResponseEntity<>(clothesService.add(clothesDto), HttpStatus.OK);
    }

    @Operation(summary = "update clothes")
    @PutMapping("/update")
    public ResponseEntity<Clothes> updateClothes(@RequestBody @Valid Clothes clothes) {
        return new ResponseEntity<>(clothesService.update(clothes), HttpStatus.OK);
    }

    @Operation(summary = "delete clothes by id")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteClothes(@PathVariable("id") Long id) {
        clothesService.delete(id);
        return HttpStatus.OK;
    }
}
