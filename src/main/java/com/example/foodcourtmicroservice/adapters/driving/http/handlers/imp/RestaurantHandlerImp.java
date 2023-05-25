package com.example.foodcourtmicroservice.adapters.driving.http.handlers.imp;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.handlers.IRestaurantHandler;

import com.example.foodcourtmicroservice.domain.api.IRestaurantExternalServicePort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImp implements IRestaurantHandler {
//    private final IRestaurantServicePort restaurantServicePort;
//    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantExternalServicePort restaurantExternalServicePort;
//    @Override
//    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
//        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto));
//    }

    @Override
    public void saveRestaurantFeign(RestaurantRequestDto restaurantRequestDto) {
        restaurantExternalServicePort.saveRestaurantServiceFeign(restaurantRequestDto);
    }
}
