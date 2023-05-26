package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.CategoryPlateEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.example.foodcourtmicroservice.domain.model.Category;
import com.example.foodcourtmicroservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryMysqlAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        CategoryPlateEntity categoryPlateEntity = categoryEntityMapper.toCategory(category);
        categoryRepository.save(categoryPlateEntity);
    }

    @Override
    public Long getCategoryByName(String name) {
        Optional<CategoryPlateEntity> categoryEntity = categoryRepository.findByName(name);
        return categoryEntity.map(CategoryPlateEntity::getId).orElseThrow();
    }

    // anadir puerto
}
