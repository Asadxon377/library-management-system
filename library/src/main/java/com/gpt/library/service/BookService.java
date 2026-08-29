package com.gpt.library.service;

import com.gpt.library.dto.request.BookRequestDTO;
import com.gpt.library.dto.response.BookResponseDTO;

import java.util.List;

public interface BookService {
    void addBook(BookRequestDTO dto);
    void updateBook(int id, BookRequestDTO dto);
    void deleteBookById(int id);
    List<BookResponseDTO> getBooks();
    BookResponseDTO getBookById(int id);
    BookRequestDTO getBookForUpdate(int id);
}
