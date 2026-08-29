package com.gpt.library.service;

import com.gpt.library.dto.request.AuthorRequestDTO;
import com.gpt.library.dto.response.AuthorResponseDTO;
import com.gpt.library.entity.Author;
import com.gpt.library.exception.AuthorHasBooksException;
import com.gpt.library.exception.AuthorNotFoundException;
import com.gpt.library.mapper.AuthorMapper;
import com.gpt.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    @Transactional
    public void addAuthor(AuthorRequestDTO dto) {
        authorRepository.save(authorMapper.toAuthor(dto));
    }

    @Override
    @Transactional
    public void updateAuthor(int id, AuthorRequestDTO dto) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException("Author with id " + id + " not found")
        );
        author.setName(dto.name());
        authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(int id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException("Author with id " + id + " not found")
        );

        if (!author.getBooks().isEmpty()) {
            throw new AuthorHasBooksException(
                    "Cannot delete author because they have books"
            );
        }
        authorRepository.delete(author);
    }

    @Override
    public List<AuthorResponseDTO> getAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toAuthorResponseDTO)
                .toList();
    }

    @Override
    public AuthorResponseDTO getAuthor(int id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException("Author with id " + id + " not found")
        );
        return authorMapper.toAuthorResponseDTO(author);
    }

    @Override
    public AuthorRequestDTO getAuthorForUpdate(int id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new AuthorNotFoundException(
                                "Author with id " + id + " not found"
                        )
                );

        return new AuthorRequestDTO(
                author.getName()
        );
    }
}
