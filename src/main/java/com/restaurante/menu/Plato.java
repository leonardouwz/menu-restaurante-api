package com.restaurante.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// Un plato del menú: 5 campos (id + 4 datos). El id lo asigna el servidor.
public record Plato(
        Long id,
        @NotBlank String nombre,
        @NotBlank String categoria,
        @NotNull @Positive Double precio,
        @NotNull Boolean disponible) {
}
