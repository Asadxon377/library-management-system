package com.gpt.library.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequestDTO(
        @NotBlank(message = "Author name cannot be blank")
        String name) {
}
