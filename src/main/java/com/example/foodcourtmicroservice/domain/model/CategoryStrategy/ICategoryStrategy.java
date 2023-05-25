package com.example.foodcourtmicroservice.domain.model.CategoryStrategy;

import com.example.foodcourtmicroservice.domain.model.Category;
import com.example.foodcourtmicroservice.domain.model.Plate;

public interface ICategoryStrategy {
    void assignCategoryDetails(Plate plate, Category category);
    Long getIdCategory();
    String getName();
    String getDescription();
}
