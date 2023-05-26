package com.example.foodcourtmicroservice.domain.usecase;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.example.foodcourtmicroservice.configuration.Constants;
import com.example.foodcourtmicroservice.domain.api.IPlateServicePort;
import com.example.foodcourtmicroservice.domain.exceptions.IdPlateNotFoundException;
import com.example.foodcourtmicroservice.domain.model.Category;
import com.example.foodcourtmicroservice.domain.model.CategoryStrategy.Categories.CategoryStrategyFactory;
import com.example.foodcourtmicroservice.domain.model.CategoryStrategy.ICategoryStrategy;
import com.example.foodcourtmicroservice.domain.model.Plate;
import com.example.foodcourtmicroservice.domain.spi.ICategoryPersistencePort;
import com.example.foodcourtmicroservice.domain.spi.IPlatePersistencePort;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class PlateUseCase implements IPlateServicePort {
    private final IPlatePersistencePort platePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public PlateUseCase(IPlatePersistencePort platePersistencePort,ICategoryPersistencePort categoryPersistencePort) {
        this.platePersistencePort = platePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void savePlate(Plate plate, Long idRestaurant) {
        Long categoryID = plate.getId_category().getId();
        CategoryStrategyFactory strategyFactory = new CategoryStrategyFactory();
        ICategoryStrategy categoryStrategy = strategyFactory.createStrategy(categoryID);
        categoryStrategy.assignCategoryDetails(plate, plate.getId_category());
        plate.setIdRestaurant(idRestaurant);
        platePersistencePort.savePlate(plate);
        saveCategory(categoryStrategy);
    }

    @Override
    public void updatePlate(Plate plate) {
        Optional<PlateEntity> optionalPlateEntity = platePersistencePort.findById(plate.getId());
        if (optionalPlateEntity.isPresent()) {
            PlateEntity existPlateEntity = optionalPlateEntity.get();
            existPlateEntity.setPrice(plate.getPrice());
            existPlateEntity.setDescription(plate.getDescription());
            platePersistencePort.updatePlate(existPlateEntity);
        } else {
            throw new IdPlateNotFoundException(Constants.ID_UPDATE_NOT_FOUND);
        }
    }

    private void saveCategory(ICategoryStrategy categoryStrategy){
        Long categoryId = categoryStrategy.getIdCategory();
        String categoryName = categoryStrategy.getName();
        String categoryDescription = categoryStrategy.getDescription();
        Category category = new Category(categoryId,categoryName,categoryDescription);
        categoryPersistencePort.saveCategory(category);
    }
}
