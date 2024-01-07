package com.leviathan.shop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Category.")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Category id", example = "1")
    private Long id;

    @NotEmpty(message = "The category 'title' mustn't be empty!")
    @Schema(description = "Category title", example = "Shoes")
    private String title;
}
