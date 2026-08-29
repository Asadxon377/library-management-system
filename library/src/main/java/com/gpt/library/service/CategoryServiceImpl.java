package com.gpt.library.service;

import com.gpt.library.dto.request.CategoryRequestDTO;
import com.gpt.library.dto.response.CategoryResponseDTO;
import com.gpt.library.entity.Category;
import com.gpt.library.exception.CategoryHasBooksException;
import com.gpt.library.exception.CategoryNotFoundException;
import com.gpt.library.mapper.CategoryMapper;
import com.gpt.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    @Transactional
    public void addCategory(CategoryRequestDTO dto) {
        categoryRepository.save(categoryMapper.toCategory(dto));
    }

    @Override
    @Transactional
    public void updateCategory(int id, CategoryRequestDTO dto) {
        Category category = categoryRepository
                .findById(id).orElseThrow(
                        () -> new CategoryNotFoundException("Category with id " + id + " not found")
                );
        category.setName(dto.name());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(int id) {
        Category category = categoryRepository
                .findById(id).orElseThrow(
                        () -> new CategoryNotFoundException("Category with id " + id + " not found")
                );
        if(!category.getBooks().isEmpty()) {
            throw new CategoryHasBooksException("Category with id " + id + " has books and cannot be deleted");
        }
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponseDTO> getCategories() {
        return categoryRepository.findAllByOrderByIdAsc()
                .stream()
                .map(categoryMapper::toCategoryResponseDTO)
                .toList();
    }

    @Override
    public CategoryResponseDTO getCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with id " + id + " not found")
        );
        return categoryMapper.toCategoryResponseDTO(category);
    }

    @Override
    public CategoryRequestDTO getCategoryForUpdate(int id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category with id " + id + " not found"
                        )
                );

        return new CategoryRequestDTO(
                category.getName()
        );
    }
}
