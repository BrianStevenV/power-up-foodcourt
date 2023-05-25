package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantExternalPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantMysqlAdapter implements IRestaurantExternalPersistencePort {
    //Encriptation here.
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

//    @Override
//    public void saveRestaurant(Restaurant restaurant) {
//        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
//        restaurantRepository.save(restaurantEntity);
//    }



    @Override
    public void saveRestaurantPersistenceFeign(RestaurantRequestDto restaurantRequestDto) {
//        feignClientRestaurantAdapter.saveRestaurantFeign(restaurantRequestDto);
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toRestaurantEntity(restaurantRequestDto);
        restaurantRepository.save(restaurantEntity);
    }
}
