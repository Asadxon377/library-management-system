package com.gpt.library.service;

import com.gpt.library.dto.request.CategoryRequestDTO;
import com.gpt.library.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    void addCategory(CategoryRequestDTO dto);

    void updateCategory(int id, CategoryRequestDTO dto);

    void deleteCategory(int id);

    List<CategoryResponseDTO> getCategories();

    CategoryResponseDTO getCategory(int id);

    CategoryRequestDTO getCategoryForUpdate(int id);
}
