package com.leviathan.shop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order.")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Order id", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Schema(description = "User.")
    private User user;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    @Schema(description = "Clothes.")
    private Clothes clothes;

    @NotEmpty(message = "The order 'address' mustn't be empty!")
    @Schema(description = "Order address.", example = "The White House 1600 Pennsylvania Avenue NW Washington, DC 20500")
    private String address;
}

