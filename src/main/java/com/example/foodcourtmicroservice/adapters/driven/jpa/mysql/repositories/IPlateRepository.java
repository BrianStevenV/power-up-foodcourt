package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {
    Optional<PlateEntity> findById(Long id);

//    @Query("SELECT p FROM PlateEntity p WHERE p.name = :name AND p.idRestaurant = :idRestaurant")
    @Query(value = "SELECT * FROM plates WHERE name = :name AND id_restaurant = :idRestaurant", nativeQuery = true)
    PlateEntity findByIdRestaurantAndName(@Param("idRestaurant")Long idRestaurant, @Param("name")String name);
}
