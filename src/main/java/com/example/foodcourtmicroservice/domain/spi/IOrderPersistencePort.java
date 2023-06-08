package com.example.foodcourtmicroservice.domain.spi;


import com.example.foodcourtmicroservice.domain.model.Order.Order;
import com.example.foodcourtmicroservice.domain.model.Order.PlateOrder;

import java.util.List;

public interface IOrderPersistencePort {
    void createOrder(Order order, List<PlateOrder> plateOrderList);
    boolean clientHasOrder(Long id);
}
