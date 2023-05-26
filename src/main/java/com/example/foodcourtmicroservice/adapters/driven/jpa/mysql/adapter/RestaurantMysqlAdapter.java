package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.exceptions.DataDuplicateViolationException;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantExternalPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

@RequiredArgsConstructor
public class RestaurantMysqlAdapter implements IRestaurantExternalPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public void saveRestaurantPersistenceFeign(RestaurantRequestDto restaurantRequestDto) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toRestaurantEntity(restaurantRequestDto);
        try{
            restaurantRepository.save(restaurantEntity);
        }   catch (DataIntegrityViolationException e){
            throw new DataDuplicateViolationException();
        }

    }

}
