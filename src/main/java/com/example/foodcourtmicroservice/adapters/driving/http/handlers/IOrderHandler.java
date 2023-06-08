package com.example.foodcourtmicroservice.adapters.driving.http.handlers;

import com.example.foodcourtmicroservice.adapters.driving.http.dto.request.Order.OrderRequestDto;

public interface IOrderHandler {
    void createOrder(OrderRequestDto order);
}
