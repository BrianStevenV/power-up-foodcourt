package com.example.foodcourtmicroservice.domain.usecase;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.RestaurantFeignClient;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.example.foodcourtmicroservice.domain.api.IRestaurantExternalServicePort;
import com.example.foodcourtmicroservice.domain.exceptions.NoProviderException;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantExternalPersistencePort;


public class FeignClientRestaurantUseCase implements IRestaurantExternalServicePort {

    private RestaurantFeignClient restaurantFeignClient;

    private IRestaurantExternalPersistencePort restaurantExternalPersistencePort;

    public FeignClientRestaurantUseCase(RestaurantFeignClient restaurantFeignClient, IRestaurantExternalPersistencePort restaurantExternalPersistencePort){
        this.restaurantFeignClient = restaurantFeignClient;
        this.restaurantExternalPersistencePort = restaurantExternalPersistencePort;
    }

    public FeignClientRestaurantUseCase(){}


    @Override
    public void saveRestaurantServiceFeign(RestaurantRequestDto restaurantRequestDto) {
        if(restaurantFeignClient.getUserByDni(restaurantRequestDto.getIdOwner()).getIdRole().getDescription().equals("PROVIDER_ROLE")){
            restaurantExternalPersistencePort.saveRestaurantPersistenceFeign(restaurantRequestDto);
        }   else {
            throw new NoProviderException();
        }
    }
}
