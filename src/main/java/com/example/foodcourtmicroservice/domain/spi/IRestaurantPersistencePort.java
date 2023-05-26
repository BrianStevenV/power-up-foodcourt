package com.example.foodcourtmicroservice.domain.spi;

public interface IRestaurantPersistencePort {
    Long getByNameRestaurant(String nameRestaurant);

}
