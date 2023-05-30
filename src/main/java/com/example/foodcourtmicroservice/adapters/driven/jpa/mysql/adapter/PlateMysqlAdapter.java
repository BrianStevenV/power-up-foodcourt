package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;
import com.example.foodcourtmicroservice.domain.model.Plate;
import com.example.foodcourtmicroservice.domain.spi.IPlatePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PlateMysqlAdapter implements IPlatePersistencePort {
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    @Override
    public void savePlate(Plate plate) {
        PlateEntity plateEntity = plateEntityMapper.toPlate(plate);
        plateRepository.save(plateEntity);
    }

    @Override
    public void updatePlate(PlateEntity plate) {
        plateRepository.save(plate);
    }

    @Override
    public Optional<PlateEntity> findById(Long id) {
        Optional<PlateEntity> plateEntity = plateRepository.findById(id);
        if (plateEntity.isPresent()) {
            return Optional.of(plateEntity.get());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void statusEnabledPlate(Boolean enabled, Plate plate) {
        PlateEntity plateEntity = plateRepository.findByIdRestaurantAndName(plate.getIdRestaurant(), plate.getName());
        System.out.println(plate.getIdRestaurant());
        System.out.println(plate.getName());
        if (plateEntity != null) {
            plateEntity.setEnabled(enabled);
            plateRepository.save(plateEntity);
        } else {
            System.out.println("ENTRE AL ELSE.");
            //TODO: COLOCAR EXEPCION EN STANUSENABLEDPLATE

        }
    }
}
