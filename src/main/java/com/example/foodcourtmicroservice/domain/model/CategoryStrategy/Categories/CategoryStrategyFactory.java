package com.example.foodcourtmicroservice.domain.model.CategoryStrategy.Categories;

import com.example.foodcourtmicroservice.configuration.Constants;
import com.example.foodcourtmicroservice.domain.model.CategoryStrategy.ICategoryStrategy;

public class CategoryStrategyFactory {
    public ICategoryStrategy createStrategy(Long categoryID){
        if (categoryID == Constants.VEGAN_DISHES) {
            return new VeganDishes();
        } else if (categoryID == Constants.SEA_DISHES) {
            return new SeaDishes();
        } else if (categoryID == Constants.MEAT_DISHES) {
            return new MeatDishes();
        }
        //personalizar exception
        throw new IllegalArgumentException("Invalid categoryId" + categoryID);
    }
}
