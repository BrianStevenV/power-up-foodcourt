package com.example.foodcourtmicroservice.OrderServiceTest;

import com.example.foodcourtmicroservice.domain.api.IAuthenticationUserInfoServicePort;
import com.example.foodcourtmicroservice.domain.exceptions.ClientHasOrderException;
import com.example.foodcourtmicroservice.domain.model.Order.Order;
import com.example.foodcourtmicroservice.domain.model.Order.PlateOrder;
import com.example.foodcourtmicroservice.domain.spi.IOrderPersistencePort;
import com.example.foodcourtmicroservice.domain.spi.IPlatePersistencePort;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
import com.example.foodcourtmicroservice.domain.usecase.OrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class OrderUseCaseTest {
    @Mock
    private IOrderPersistencePort orderPersistencePort;
    @Mock
    private IPlatePersistencePort platePersistencePort;
    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;
    private IAuthenticationUserInfoServicePort authenticationUserInfoServicePort;

    private OrderUseCase orderUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationUserInfoServicePort = Mockito.mock(IAuthenticationUserInfoServicePort.class);
        orderUseCase = new OrderUseCase(orderPersistencePort, platePersistencePort, restaurantPersistencePort, authenticationUserInfoServicePort);
    }


    @Test
    @DisplayName("Test: createOrder - Successful")
    public void createOrderSuccessfulTest() {
        // Arrange
        String nameRestaurant = "Restaurant A";
        Long clientId = 123L;
        Long plateId = 456L;
        Long orderId = 789L;

        List<PlateOrder> plateOrderList = new ArrayList<>();
        plateOrderList.add(new PlateOrder(orderId, plateId, 2));

        when(authenticationUserInfoServicePort.getIdUserFromToken()).thenReturn(clientId);
        when(orderPersistencePort.clientHasOrder(clientId)).thenReturn(false);
        when(platePersistencePort.findById(plateId)).thenReturn(Optional.of(plateId));
        when(restaurantPersistencePort.getByNameRestaurant(nameRestaurant)).thenReturn(1L);

        // Act
        orderUseCase.createOrder(nameRestaurant, plateOrderList);

        // Assert
        verify(authenticationUserInfoServicePort).getIdUserFromToken();
        verify(orderPersistencePort).clientHasOrder(clientId);
        verify(platePersistencePort).findById(plateId);
        verify(restaurantPersistencePort).getByNameRestaurant(nameRestaurant);
        verify(orderPersistencePort).createOrder(any(Order.class), eq(plateOrderList));
        verifyNoMoreInteractions(authenticationUserInfoServicePort, orderPersistencePort, platePersistencePort, restaurantPersistencePort);
    }
    @Test
    @DisplayName("Test: createOrder - Failure (Exception)")
    public void createOrderFailureTest() {
        // Arrange
        String nameRestaurant = "Restaurant A";
        Long clientId = 123L;
        Long plateId = 456L;
        Long orderId = 789L;

        List<PlateOrder> plateOrderList = new ArrayList<>();
        plateOrderList.add(new PlateOrder(orderId, plateId, 2));

        when(authenticationUserInfoServicePort.getIdUserFromToken()).thenReturn(clientId);
        when(orderPersistencePort.clientHasOrder(clientId)).thenReturn(true);

        // Act and Assert
        assertThrows(ClientHasOrderException.class, () -> orderUseCase.createOrder(nameRestaurant, plateOrderList));

        verify(authenticationUserInfoServicePort).getIdUserFromToken();
        verify(orderPersistencePort).clientHasOrder(clientId);
        verifyNoMoreInteractions(authenticationUserInfoServicePort, orderPersistencePort, platePersistencePort, restaurantPersistencePort);
    }

    //More?


}
