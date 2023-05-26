package com.example.foodcourtmicroservice.adapters.driving.http.handlers;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.UpdatePlateRequestDto;

public interface IPlateHandler {
    void savePlate(PlateRequestDto plateRequestDto, Long idRestaurant);
    void updatePlate(UpdatePlateRequestDto updatePlateRequestDto);
}
