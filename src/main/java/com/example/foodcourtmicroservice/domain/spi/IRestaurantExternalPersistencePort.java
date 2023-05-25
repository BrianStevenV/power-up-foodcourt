package com.example.foodcourtmicroservice.domain.spi;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;

public interface IRestaurantExternalPersistencePort {
    void saveRestaurantPersistenceFeign(RestaurantRequestDto restaurantRequestDto);
}
