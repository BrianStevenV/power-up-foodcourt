package com.example.foodcourtmicroservice.domain.spi;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.example.foodcourtmicroservice.domain.model.Plate;

import java.util.Optional;

public interface IPlatePersistencePort {
    void savePlate(Plate plate);
    void updatePlate(PlateEntity plate);
    Optional<PlateEntity> findById(Long id);

    void statusEnabledPlate(Boolean enabled, Plate plate);
}
