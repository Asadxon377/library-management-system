package com.gpt.library.service;

import com.gpt.library.dto.request.BookRequestDTO;
import com.gpt.library.dto.response.BookResponseDTO;
import com.gpt.library.entity.Author;
import com.gpt.library.entity.Book;
import com.gpt.library.entity.Category;
import com.gpt.library.exception.AuthorNotFoundException;
import com.gpt.library.exception.BookNotFoundException;
import com.gpt.library.exception.CategoryNotFoundException;
import com.gpt.library.mapper.BookMapper;
import com.gpt.library.repository.AuthorRepository;
import com.gpt.library.repository.BookRepository;
import com.gpt.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transactional
    public void addBook(BookRequestDTO dto) {
        Author author =
                authorRepository.findById(dto.authorId()).orElseThrow(
                        ()-> new AuthorNotFoundException("Author not found with id " + dto.authorId())
                );

        Category category =
                categoryRepository.findById(dto.categoryId()).orElseThrow(
                        ()-> new CategoryNotFoundException("Category not found with id " + dto.categoryId())
                );



        Book book= bookMapper.toBook(dto,author, category);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void updateBook(int id, BookRequestDTO dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with id " + id
                        )
                );

        Author author = authorRepository.findById(dto.authorId())
                .orElseThrow(() ->
                        new AuthorNotFoundException(
                                "Author not found with id " + dto.authorId()
                        )
                );

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id " + dto.categoryId()
                        )
                );

        bookMapper.updateBook(book, dto, author, category);
    }

    @Override
    @Transactional
    public void deleteBookById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with id " + id
                        )
                );

        bookRepository.delete(book);
    }

    @Override
    public List<BookResponseDTO> getBooks() {
        return bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::isAvailable).reversed())
                .map(bookMapper::toBookResponseDTO)
                .toList();
    }


    @Override
    public BookResponseDTO getBookById(int id) {
        return bookMapper.toBookResponseDTO(bookRepository.findById(id).orElseThrow(
                ()-> new BookNotFoundException("Book not found with id " + id)
        ));
    }

    @Override
    public BookRequestDTO getBookForUpdate(int id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with id " + id
                        )
                );

        return new BookRequestDTO(
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getAuthor().getId(),
                book.getCategory().getId()
        );
    }
}
