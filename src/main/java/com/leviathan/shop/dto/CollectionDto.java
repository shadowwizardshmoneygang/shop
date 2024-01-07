package com.leviathan.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "Collection data transfer object.")
public class CollectionDto {
    @NotEmpty(message = "The 'title' mustn't be empty!")
    @Schema(description = "Collection title.", example = "Religion")
    private String title;

    @NotEmpty(message = "The 'description' mustn't be empty!")
    @Schema(description = "Collection description.", example = "God is dead")
    private String description;
}
