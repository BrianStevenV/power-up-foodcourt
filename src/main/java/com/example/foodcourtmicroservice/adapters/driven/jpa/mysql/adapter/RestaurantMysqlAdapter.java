package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantMysqlAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;


    @Override
    public Long getByNameRestaurant(String nameRestaurant) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findByName(nameRestaurant);
        return restaurantEntity.map(RestaurantEntity::getId).orElseThrow();
    }
}
