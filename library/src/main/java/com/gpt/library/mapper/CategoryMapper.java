package com.gpt.library.mapper;

import com.gpt.library.dto.request.CategoryRequestDTO;
import com.gpt.library.dto.response.CategoryResponseDTO;
import com.gpt.library.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryRequestDTO dto){
        return new Category(dto.name());
    }
    public CategoryResponseDTO toCategoryResponseDTO(Category category){
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}
