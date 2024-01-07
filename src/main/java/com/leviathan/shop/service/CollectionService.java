package com.leviathan.shop.service;

import com.leviathan.shop.dto.CollectionDto;
import com.leviathan.shop.entity.Collection;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.repository.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public List<Collection> getAll() {
        return collectionRepository.findAll();
    }

    public Collection get(Long id) throws NotFoundException {
        return collectionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Collection with id '" + id + "' doesn't exist!")
        );
    }

    public Collection add(CollectionDto collectionDto) {
        return collectionRepository.save(
                Collection.builder()
                        .title(collectionDto.getTitle())
                        .description(collectionDto.getDescription())
                        .build()
        );
    }

    public Collection update(Collection collection) {
        return collectionRepository.save(collection);
    }

    public void delete(Long id) {
        collectionRepository.deleteById(id);
    }
}
