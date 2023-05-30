package com.example.foodcourtmicroservice.adapters.driving.http.handlers;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.PlateStatusUpdateRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.UpdatePlateRequestDto;

public interface IPlateHandler {
    void savePlate(PlateRequestDto plateRequestDto, Long idRestaurant, String categoryPlate);
    void updatePlate(UpdatePlateRequestDto updatePlateRequestDto);
    void statusEnabledPlate(Boolean enabled, PlateStatusUpdateRequestDto plateStatus);
}
