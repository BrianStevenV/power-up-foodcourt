package com.example.foodcourtmicroservice.adapters.driving.http.handlers.imp;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.Order.OrderRequestDto;
import com.example.foodcourtmicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.example.foodcourtmicroservice.adapters.driving.http.mappers.IPlateOrderRequestMapper;
import com.example.foodcourtmicroservice.domain.api.IOrderServicePort;
import com.example.foodcourtmicroservice.domain.model.Order.PlateOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHandlerImp implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IPlateOrderRequestMapper plateOrderMapper;
    @Override
    public void createOrder(OrderRequestDto orderRequestDto) {
        List<PlateOrder> plateOrderList = new ArrayList<>();
        orderRequestDto.getPlateOrderRequestDtoList().forEach(plate -> plateOrderList.add(plateOrderMapper.toPlateOrder(plate)));
        orderServicePort.createOrder(orderRequestDto.getNameRestaurant(), plateOrderList);
    }
}
