package com.gpt.library.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "please fill out the category field")
        String name) {
}
