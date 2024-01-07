package com.leviathan.shop.controller;

import com.leviathan.shop.dto.CollectionDto;
import com.leviathan.shop.entity.Collection;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Collection")
@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;

    @Operation(summary = "get all collections")
    @GetMapping("/all")
    public ResponseEntity<List<Collection>> getCollections() {
        return new ResponseEntity<>(collectionService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "get collection by id")
    @GetMapping("/{id}")
    public ResponseEntity<Collection> getCollection(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(collectionService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "add collection")
    @PostMapping("/add")
    public ResponseEntity<Collection> addCollection(@RequestBody @Valid CollectionDto collectionDto) {
        return new ResponseEntity<>(collectionService.add(collectionDto), HttpStatus.OK);
    }

    @Operation(summary = "update collection")
    @PutMapping("/update")
    public ResponseEntity<Collection> updateCollection(@RequestBody @Valid Collection collection) {
        return new ResponseEntity<>(collectionService.update(collection), HttpStatus.OK);
    }

    @Operation(summary = "delete collection by id")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteCollection(@PathVariable("id") Long id) {
        collectionService.delete(id);
        return HttpStatus.OK;
    }
}
