package com.example.foodcourtmicroservice.domain.api;

import com.example.foodcourtmicroservice.domain.model.Order.PlateOrder;

import java.util.List;

public interface IOrderServicePort {
    void createOrder(String nameRestaurant, List<PlateOrder> plateOrderList);
}
