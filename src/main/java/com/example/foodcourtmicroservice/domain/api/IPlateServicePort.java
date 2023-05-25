package com.example.foodcourtmicroservice.domain.api;

import com.example.foodcourtmicroservice.domain.model.Plate;

public interface IPlateServicePort {
    void savePlate(Plate plate);
    void updatePlate(Plate plate);
}
