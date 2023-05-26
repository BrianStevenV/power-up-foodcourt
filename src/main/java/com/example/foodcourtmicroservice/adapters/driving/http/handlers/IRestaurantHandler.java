package com.example.foodcourtmicroservice.adapters.driving.http.handlers;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;

public interface IRestaurantHandler {
    void saveRestaurantFeign(RestaurantRequestDto restaurantRequestDto);

    Long getByNameRestaurant(String nameRestaurant);
}
