package com.gpt.library.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public String bookNotFound(BookNotFoundException e) {
        return "book-not-found";
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public String categoryNotFound(CategoryNotFoundException e) {
        return "category-not-found";
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public String authorNotFound(AuthorNotFoundException e) {
        return "author-not-found";
    }
    @ExceptionHandler(AuthorHasBooksException.class)
    public String authorHasBooks(AuthorHasBooksException e) {
        return "author-has-books";
    }
    @ExceptionHandler(CategoryHasBooksException.class)
    public String categoryHasBooks(CategoryHasBooksException e) {
        return "category-has-books";
    }
}
