package com.leviathan.shop.repository;

import com.leviathan.shop.entity.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    List<Clothes> findByCollectionId(Long id);
    List<Clothes> findByCategoryId(Long id);
}
