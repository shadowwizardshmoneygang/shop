package com.leviathan.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "User data transfer object.")
public class UserDto {
    @NotEmpty(message = "The 'name' mustn't be empty!")
    @Schema(description = "User name.", example = "Anton")
    private String name;

    @Pattern(
            regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Incorrect phone number entry!"
    )
    @Schema(description = "User phone.", example = "88005353535")
    private String phone;
}
