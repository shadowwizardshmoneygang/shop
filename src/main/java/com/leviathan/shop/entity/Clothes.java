package com.leviathan.shop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Clothes.")
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Clothes id", example = "1")
    private Long id;

    @NotEmpty(message = "The clothes 'title' mustn't be empty!")
    @Schema(description = "Clothes title.", example = "Magnolia")
    private String title;

    @NotEmpty(message = "The clothes 'description' mustn't be empty!")
    @Schema(description = "Clothes description.", example = "Suckless shoes")
    private String description;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    @Schema(description = "Collection.")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Schema(description = "Category.")
    private Category category;

    @NotNull(message = "The clothes 'amount' mustn't be empty!")
    @Schema(description = "Clothes amount.", example = "100")
    private Integer amount;

    @NotNull(message = "The clothes 'price' mustn't be empty!")
    @Schema(description = "Clothes price.", example = "100.00")
    private BigDecimal price;
}
