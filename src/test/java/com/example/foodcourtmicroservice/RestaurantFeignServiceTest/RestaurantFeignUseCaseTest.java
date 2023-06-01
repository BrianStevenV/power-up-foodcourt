package com.example.foodcourtmicroservice.RestaurantFeignServiceTest;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.RestaurantFeignClient;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.response.RoleResponseDto;
import com.example.foodcourtmicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.example.foodcourtmicroservice.domain.exceptions.NoProviderException;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantExternalPersistencePort;
import com.example.foodcourtmicroservice.domain.usecase.FeignClientRestaurantUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class RestaurantFeignUseCaseTest {
    @Mock
    private IRestaurantExternalPersistencePort restaurantExternalPersistencePort;
    @Mock
    private RestaurantFeignClient restaurantFeignClient;
    private FeignClientRestaurantUseCase feignClientRestaurantUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        feignClientRestaurantUseCase = new FeignClientRestaurantUseCase(restaurantFeignClient,restaurantExternalPersistencePort);
    }

    @Test
    public void getUserByDniMethodSuccessfulTest() {
        UserResponseDto userResponseDto = new UserResponseDto("123", "Prueba", "Prueba apellido",
                "email@example.com", "3126805081", LocalDate.of(2023, 3, 30),
                "string", new RoleResponseDto("PROVIDER_ROLE", "PROVIDER_ROLE"));
        when(restaurantFeignClient.getUserByDni(anyString())).thenReturn(userResponseDto);
        String dniNumber = "123";
        UserResponseDto result = restaurantFeignClient.getUserByDni(dniNumber);
        assertNotNull(result);
        assertEquals("123", result.getDniNumber());
        assertEquals("Prueba", result.getName());
        assertEquals("Prueba apellido", result.getSurname());
        verify(restaurantFeignClient).getUserByDni(dniNumber);
    }

    @Test
    public void getUserByDniMethodFailureTest(){
        UserResponseDto userResponseDto = new UserResponseDto("123", "Prueba", "Prueba apellido",
                "email@example.com", "3126805081", LocalDate.of(2023, 3, 30),
                "string", new RoleResponseDto("PROVIDER_ROLE", "PROVIDER_ROLE"));
        when(restaurantFeignClient.getUserByDni(anyString()))
                .thenThrow(NoProviderException.class);
        String dniNumber = "456";
        assertThrows(NoProviderException.class,
                () -> restaurantFeignClient.getUserByDni(dniNumber));

        verify(restaurantFeignClient).getUserByDni(dniNumber);
    }

    @Test
    public void saveRestaurantServiceFeignTest(){
        UserResponseDto userResponseDto = new UserResponseDto("123", "Prueba", "Prueba apellido",
                "email@example.com", "3126805081", LocalDate.of(2023, 3, 30),
                "string", new RoleResponseDto("PROVIDER_ROLE", "PROVIDER_ROLE"));
        when(restaurantFeignClient.getUserByDni(anyString())).thenReturn(userResponseDto);
        String dniNumber = "123";
        UserResponseDto result = restaurantFeignClient.getUserByDni(dniNumber);
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto("Prueba","Java Street","3192621110",
                "Image","34512","4");
        feignClientRestaurantUseCase.saveRestaurantServiceFeign(restaurantRequestDto);
        verify(restaurantExternalPersistencePort).saveRestaurantPersistenceFeign(restaurantRequestDto);

    }

    // NO SE HACE VALIDACIONES SI LA INFORMACION DE RESTAURANTREQUESTDTO ES CORRECTA POR QUE ESO SE ENCARGA EL @VALID EN EL CONTROLLER

}
