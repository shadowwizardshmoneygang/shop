package com.leviathan.shop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collections")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Collection.")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Collection id", example = "1")
    private Long id;

    @NotEmpty(message = "The collection 'title' mustn't be empty!")
    @Schema(description = "Collection title.", example = "Religion")
    private String title;

    @NotEmpty(message = "The collection 'description' mustn't be empty!")
    @Schema(description = "Collection description.", example = "God is dead")
    private String description;
}
