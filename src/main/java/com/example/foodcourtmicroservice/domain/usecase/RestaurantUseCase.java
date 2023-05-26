package com.example.foodcourtmicroservice.domain.usecase;

import com.example.foodcourtmicroservice.domain.api.IRestaurantServicePort;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort){ this.restaurantPersistencePort = restaurantPersistencePort;}
    @Override
    public Long getByNameRestaurant(String nameRestaurant) {
        return restaurantPersistencePort.getByNameRestaurant(nameRestaurant);
    }
}
