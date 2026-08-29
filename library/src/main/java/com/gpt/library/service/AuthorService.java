package com.gpt.library.service;

import com.gpt.library.dto.request.AuthorRequestDTO;
import com.gpt.library.dto.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {

    void addAuthor(AuthorRequestDTO dto);

    void updateAuthor(int id, AuthorRequestDTO dto);

    void deleteAuthor(int id);

    List<AuthorResponseDTO> getAuthors();

    AuthorResponseDTO getAuthor(int id);

    AuthorRequestDTO getAuthorForUpdate(int id);
}
