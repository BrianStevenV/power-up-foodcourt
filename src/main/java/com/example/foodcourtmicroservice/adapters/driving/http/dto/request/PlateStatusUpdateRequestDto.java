package com.example.foodcourtmicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class PlateStatusUpdateRequestDto {
    private String name;
    private Long idRestaurant;
}
