package com.leviathan.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Order data transfer object.")
public class OrderDto {
    @NotNull(message = "The 'user_id' mustn't be empty!")
    @Schema(description = "User id.", example = "1")
    private Long userId;

    @NotNull(message = "The 'clothes_id' mustn't be empty!")
    @Schema(description = "Clothes id.", example = "1")
    private Long clothesId;

    @NotEmpty(message = "The 'address' mustn't be empty!")
    @Schema(description = "Order address.", example = "The White House 1600 Pennsylvania Avenue NW Washington, DC 20500")
    private String address;
}
