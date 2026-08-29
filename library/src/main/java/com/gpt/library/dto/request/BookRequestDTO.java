package com.gpt.library.dto.request;

import jakarta.validation.constraints.*;

public record BookRequestDTO(

        @NotBlank(message = "title field is necessary")
        String title,

        @NotBlank(message = "isbn field is necessary")
        String isbn,

        @NotNull(message = "please fill this field")
        @Min(value = 1500, message="publication year should be at least 1500")
        @Max(value = 2026, message = "publication year should not be bigger than current year")
        Integer publicationYear,

        @NotNull(message = "Please select an author")
        @Positive(message = "Please select a valid author")
        Integer authorId,

        @NotNull(message = "Please select a category")
        @Positive(message = "Please select a valid category")
        Integer categoryId
) {
}