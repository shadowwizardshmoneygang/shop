package com.leviathan.shop.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Error response.")
public class ErrorResponse {
    @Schema(description = "Error code.", example = "204")
    private Integer code;

    @Schema(description = "Error message.", example = "Category with id '333' doesn't exist!")
    private String message;
}
