package com.example.foodcourtmicroservice.adapters.driving.http.handlers.imp;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.UpdatePlateRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.example.foodcourtmicroservice.adapters.driving.http.mappers.IPlateRequestMapper;
import com.example.foodcourtmicroservice.domain.api.IPlateServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlateHandlerImp implements IPlateHandler {
    private final IPlateServicePort plateServicePort;
    private final IPlateRequestMapper plateRequestMapper;

    @Override
    public void savePlate(PlateRequestDto plateRequestDto) {
        plateServicePort.savePlate(plateRequestMapper.toPlate(plateRequestDto));
    }

    @Override
    public void updatePlate(UpdatePlateRequestDto updatePlateRequestDto) {
        plateServicePort.updatePlate(plateRequestMapper.toUpdatePlate(updatePlateRequestDto));
    }
}
