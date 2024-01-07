package com.leviathan.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "Category data transfer object.")
public class CategoryDto {
    @NotEmpty(message = "The 'title' mustn't be empty!")
    @Schema(description = "Category title", example = "Shoes")
    private String title;
}
