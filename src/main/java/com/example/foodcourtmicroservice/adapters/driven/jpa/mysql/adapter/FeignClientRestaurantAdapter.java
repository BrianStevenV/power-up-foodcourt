package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.mappers.IRestaurantRequestMapper;
import com.example.foodcourtmicroservice.configuration.Constants;
import com.example.foodcourtmicroservice.domain.api.IRestaurantExternalServicePort;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantExternalPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


//@Component

public class FeignClientRestaurantAdapter implements IRestaurantExternalServicePort {
    @Autowired
    private RestaurantFeignClient restaurantFeignClient;
    @Autowired
    private IRestaurantExternalPersistencePort restaurantExternalPersistencePort;
//    private final IRestaurantRequestMapper restaurantRequestMapper;
//    @Lazy
//    public FeignClientRestaurantAdapter(RestaurantFeignClient restaurantFeignClient, IRestaurantExternalPersistencePort restaurantExternalPersistencePort, IRestaurantRequestMapper restaurantRequestMapper) {
//        this.restaurantFeignClient = restaurantFeignClient;
//        this.restaurantExternalPersistencePort = restaurantExternalPersistencePort;
//        this.restaurantRequestMapper = restaurantRequestMapper;
//    }




    public FeignClientRestaurantAdapter(RestaurantFeignClient restaurantFeignClient, IRestaurantExternalPersistencePort restaurantExternalPersistencePort){
        this.restaurantFeignClient = restaurantFeignClient;
        this.restaurantExternalPersistencePort = restaurantExternalPersistencePort;
    }

    public FeignClientRestaurantAdapter(){}




//    public FeignClientRestaurantAdapter(IRestaurantExternalPersistencePort restaurantExternalPersistencePort){
//        this.restaurantExternalPersistencePort = restaurantExternalPersistencePort;
//    }

    @Override
    public void saveRestaurantServiceFeign(RestaurantRequestDto restaurantRequestDto) {
        if(restaurantFeignClient.getUserByDni(restaurantRequestDto.getIdOwner()).getIdRole().getName().equals(Constants.PROVIDER_DESCRPTION)){
            restaurantExternalPersistencePort.saveRestaurantPersistenceFeign(restaurantRequestDto);
        }
    }
}
