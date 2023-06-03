package com.example.foodcourtmicroservice.PlateServiceTest;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.exceptions.CategoryNotFoundException;
import com.example.foodcourtmicroservice.domain.api.IAuthenticationUserInfoServicePort;
import com.example.foodcourtmicroservice.domain.exceptions.IdPlateNotFoundException;
import com.example.foodcourtmicroservice.domain.exceptions.PlateNotFoundException;
import com.example.foodcourtmicroservice.domain.model.Plate;
import com.example.foodcourtmicroservice.domain.spi.ICategoryPersistencePort;
import com.example.foodcourtmicroservice.domain.spi.IPlatePersistencePort;
import com.example.foodcourtmicroservice.domain.usecase.PlateUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class PlateUseCaseTest {
    @Mock
    private IPlatePersistencePort platePersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    private IAuthenticationUserInfoServicePort authenticationUserInfoServicePort;

    private PlateUseCase plateUseCase;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        plateUseCase = new PlateUseCase(platePersistencePort, categoryPersistencePort, authenticationUserInfoServicePort);
    }

    @Test
    public void getCategoryByNameMethodSucesfullTest(){
        String nameCategory = "VEGAN_CATEGORY";
        Long idCategory = 123L;
        when(categoryPersistencePort.getCategoryByName(nameCategory)).thenReturn(idCategory);
        Long actualCategoryId = categoryPersistencePort.getCategoryByName(nameCategory);
        assertEquals(idCategory, actualCategoryId);
        verify(categoryPersistencePort).getCategoryByName(nameCategory);
    }

    @Test
    public void getCategoryByNameMethodFailureTest(){
        String nameCategory = "NO_CATEGORY";
        when(categoryPersistencePort.getCategoryByName(nameCategory)).thenThrow(CategoryNotFoundException.class);
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryPersistencePort.getCategoryByName(nameCategory);
        });
        verify(categoryPersistencePort).getCategoryByName(nameCategory);
    }

    @Test
    public void savePlateSuccessfulTest(){
        Plate plate = new Plate(123L, "Prueba", "Food Test", 50.0, "Image.http", true,3L, 14L);
        Long idRestaurant = 14L;
        String categoryPlate = "Category Test";
        Long expectedCategoryId = 3L;
        when(categoryPersistencePort.getCategoryByName(categoryPlate)).thenReturn(expectedCategoryId);
        doNothing().when(platePersistencePort).savePlate(plate);
        plateUseCase.savePlate(plate, idRestaurant, categoryPlate);
        verify(categoryPersistencePort).getCategoryByName(categoryPlate);
        verify(platePersistencePort).savePlate(plate);
    }

    @Test
    public void getFindByIdMethodTest(){
        Long id = 123L;
        PlateEntity plateEntity = new PlateEntity(id, "Mazorquita", "Great Food", 75.0,"image.http",true,3L,14L);
        when(platePersistencePort.findById(id)).thenReturn(Optional.of(plateEntity));
        Optional<PlateEntity> optionalPlateEntity = platePersistencePort.findById(id);
        assertTrue(optionalPlateEntity.isPresent());
        PlateEntity retrievedPlateEntity = optionalPlateEntity.get();
        assertEquals(id, retrievedPlateEntity.getId());
        assertEquals("Mazorquita", retrievedPlateEntity.getName());
        verify(platePersistencePort).findById(id);
    }

    @Test
    public void getFindByIdMethodFailureTest() {
        Long id = 123L;
        when(platePersistencePort.findById(id)).thenReturn(Optional.empty());
        Optional<PlateEntity> optionalPlateEntity = platePersistencePort.findById(id);
        assertFalse(optionalPlateEntity.isPresent()); // Verificar que el Optional está vacío
        verify(platePersistencePort).findById(id);
    }

    @Test
    public void updatePlateServiceExceptionTest() {
        Long id = 123L;
        when(platePersistencePort.findById(id)).thenReturn(Optional.empty());
        Plate plate = new Plate();
        plate.setId(id);
        plate.setPrice(50.0);
        plate.setDescription("Updated Plate");
        assertThrows(IdPlateNotFoundException.class, () -> plateUseCase.updatePlate(plate));
        verify(platePersistencePort).findById(id);
        verify(platePersistencePort, never()).savePlate(any(Plate.class));
    }

    @Test
    public void updateStatusPlateSucesfullTest() {
        Boolean enabled = true;
        Plate plate = new Plate(123L, "Prueba", "Food Test", 50.0, "Image.http", true, 3L, 14L);
        Plate updatedPlate = new Plate(123L, "Prueba", "Food Test", 50.0, "Image.http", enabled, 3L, 14L);
        when(platePersistencePort.statusEnabledPlate(any(Plate.class))).thenReturn(plate);
        ArgumentCaptor<Plate> plateCaptor = ArgumentCaptor.forClass(Plate.class);
        plateUseCase.statusEnabledPlate(enabled, plate);
        verify(platePersistencePort).statusEnabledPlate(any(Plate.class));
        verify(platePersistencePort).savePlate(plateCaptor.capture());
        Plate capturedPlate = plateCaptor.getValue();
        assertNotEquals(updatedPlate, capturedPlate);
    }


    @Test
    public void updateStatusPlateThrowsPlateNotFoundExceptionTest() {
        Boolean enabled = true;
        Plate plate = new Plate(123L, "Prueba", "Food Test", 50.0, "Image.http", true,3L, 14L);
        when(platePersistencePort.statusEnabledPlate(any(Plate.class))).thenReturn(null);
        assertThrows(PlateNotFoundException.class, () -> {
            plateUseCase.statusEnabledPlate(enabled,plate);
        });
        verify(platePersistencePort).statusEnabledPlate(any(Plate.class));
    }



}
