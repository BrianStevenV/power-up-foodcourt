package com.example.foodcourtmicroservice.domain.spi;

import com.example.foodcourtmicroservice.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    Long getCategoryByName(String name);
}
