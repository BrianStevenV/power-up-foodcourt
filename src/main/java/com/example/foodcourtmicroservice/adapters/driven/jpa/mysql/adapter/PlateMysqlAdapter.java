package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.response.PlatePaginationResponseDto;
import com.example.foodcourtmicroservice.domain.model.Plate;
import com.example.foodcourtmicroservice.domain.spi.IPlatePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public Optional<PlateEntity> findById(Long id) {
        Optional<PlateEntity> plateEntity = plateRepository.findById(id);
        return plateEntity;
    }
    
    @Override
    public void statusEnabledPlate(Boolean enabled, Plate plate) {
        PlateEntity plateEntity = plateRepository.findByIdRestaurantAndName(plate.getIdRestaurant(), plate.getName());
        if (plateEntity != null) {
            plateEntity.setEnabled(enabled);
            plateRepository.save(plateEntity);
        } else {
            System.out.println("ENTRE AL ELSE.");
            //TODO: COLOCAR EXEPCION EN STANUSENABLEDPLATE

        }
    }

    @Override
    public Page<PlatePaginationResponseDto> getPaginationPlates(Long idRestaurant, Integer pageSize, String sortBy, Long idCategory) {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(sortBy).ascending());
        Page<PlateEntity> platePage;
        if (idCategory != null) {
            platePage = plateRepository.findByRestaurantIdAndCategoryId(idRestaurant, idCategory, pageable);
        } else {
            platePage = plateRepository.findByRestaurantId(idRestaurant, pageable);
        }
        return platePage.map(plateEntityMapper::toPlatePaginationResponseDto);
    }

}
