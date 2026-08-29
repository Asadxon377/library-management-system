package com.gpt.library.dto.response;

public record BookResponseDTO(
        int id,
        String title,
        String isbn,
        Integer publicationYear,
        boolean available,
        Integer authorId,
        String authorName,
        Integer categoryId,
        String categoryName
) {
}