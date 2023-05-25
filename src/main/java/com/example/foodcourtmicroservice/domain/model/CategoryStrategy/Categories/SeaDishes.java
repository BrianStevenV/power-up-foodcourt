package com.example.foodcourtmicroservice.domain.model.CategoryStrategy.Categories;

import com.example.foodcourtmicroservice.configuration.Constants;
import com.example.foodcourtmicroservice.domain.model.Category;
import com.example.foodcourtmicroservice.domain.model.CategoryStrategy.ICategoryStrategy;
import com.example.foodcourtmicroservice.domain.model.Plate;

public class SeaDishes implements ICategoryStrategy {
    @Override
    public void assignCategoryDetails(Plate plate, Category category) {
        plate.getId_category().setName(Constants.NAME_SEA_DISHES);
        plate.getId_category().setDescription(Constants.DESCRIPTION_SEA_DISHES);
    }

    @Override
    public Long getIdCategory() {
        return Constants.SEA_DISHES;
    }

    @Override
    public String getName() {
        return Constants.NAME_SEA_DISHES;
    }

    @Override
    public String getDescription() {
        return Constants.DESCRIPTION_SEA_DISHES;
    }
}
