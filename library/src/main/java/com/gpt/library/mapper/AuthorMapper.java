package com.gpt.library.mapper;

import com.gpt.library.dto.request.AuthorRequestDTO;
import com.gpt.library.dto.response.AuthorResponseDTO;
import com.gpt.library.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toAuthor(AuthorRequestDTO dto) {
        return new Author(dto.name());
    }

    public AuthorResponseDTO toAuthorResponseDTO(Author author) {
        return new AuthorResponseDTO(author.getId(), author.getName());
    }
}
