package com.example.foodcourtmicroservice.adapters.driving.http.controller;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.UpdatePlateRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.example.foodcourtmicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.example.foodcourtmicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/restaurant/")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;

    private final IPlateHandler plateHandler;
    @Operation(summary = "Add a new Restaurant",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Restaurant created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Restaurant already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PreAuthorize("hasAuthority('ADMINISTRATOR_ROLE')")
    @PostMapping
    public ResponseEntity<Map<String, String>> createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurantFeign(restaurantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.RESTAURANT_CREATED_MESSAGE));
    }
    @Operation(summary = "Add a new Plate",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plate created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Plate already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PreAuthorize("hasAuthority('PROVIDER_ROLE')")
    @PostMapping("plate")
    public ResponseEntity<Map<String,String>> createPlate(@RequestBody PlateRequestDto plateRequestDto){
        plateHandler.savePlate(plateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_CREATED_MESSAGE));

    }
    @Operation(summary = "Updated a Plate",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plate updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Plate already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PreAuthorize("hasAuthority('PROVIDER_ROLE')")
    @PutMapping
    public ResponseEntity<Map<String,String>> updatePlate(@Valid @RequestBody UpdatePlateRequestDto updatePlateRequestDto){
        plateHandler.updatePlate(updatePlateRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.UPDATE_PLATE_OK));
    }
}
