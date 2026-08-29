package com.gpt.library.mapper;

import com.gpt.library.dto.request.BookRequestDTO;
import com.gpt.library.dto.response.BookResponseDTO;
import com.gpt.library.entity.Author;
import com.gpt.library.entity.Book;
import com.gpt.library.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toBook(BookRequestDTO dto, Author author, Category category) {
        return new Book(dto.title(),
                        dto.isbn(),
                        dto.publicationYear(),
                        true,
                        author,
                        category);
    }

    public BookResponseDTO toBookResponseDTO(Book book){
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.isAvailable(),
                book.getAuthor().getId(),
                book.getAuthor().getName(),
                book.getCategory().getId(),
                book.getCategory().getName());
    }
    public void updateBook(
            Book book,
            BookRequestDTO dto,
            Author author,
            Category category) {

        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPublicationYear(dto.publicationYear());
        book.setAuthor(author);
        book.setCategory(category);
    }
}
