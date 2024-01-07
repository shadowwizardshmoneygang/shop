package com.leviathan.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Clothes data transfer object.")
public class ClothesDto {
    @NotEmpty(message = "The 'title' mustn't be empty!")
    @Schema(description = "Clothes title.", example = "Magnolia")
    private String title;

    @NotEmpty(message = "The 'description' mustn't be empty!")
    @Schema(description = "Clothes description.", example = "Suckless shoes")
    private String description;

    @Schema(description = "Clothes collection.", example = "1")
    private Long collectionId;

    @Schema(description = "Clothes category.", example = "1")
    private Long categoryId;

    @NotNull(message = "The 'amount' mustn't be empty!")
    @Schema(description = "Clothes amount.", example = "100")
    private Integer amount;

    @NotNull(message = "The 'price' mustn't be empty!")
    @Schema(description = "Clothes price.", example = "100.00")
    private BigDecimal price;
}
