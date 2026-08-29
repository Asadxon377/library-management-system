package com.gpt.library.exception;

public class CategoryHasBooksException extends RuntimeException {
    public CategoryHasBooksException(String message) {
        super(message);
    }
}
