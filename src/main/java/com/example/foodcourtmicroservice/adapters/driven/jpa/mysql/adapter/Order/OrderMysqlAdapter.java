package com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.Order;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.Order.OrderEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.Order.OrderPlateEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.Order.IOrderEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.Order.IOrderPlateEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IOrderPlateRepository;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.example.foodcourtmicroservice.domain.model.Order.Order;
import com.example.foodcourtmicroservice.domain.model.Order.PlateOrder;
import com.example.foodcourtmicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderPlateRepository orderPlateRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderPlateEntityMapper orderPlateEntityMapper;
    @Override
    public void createOrder(Order order, List<PlateOrder> plateOrderList) {
        OrderEntity orderEntity = orderEntityMapper.toOrderEntity(order);
        orderRepository.save(orderEntity);
        List<OrderPlateEntity> orderPlateEntityList = new ArrayList<>();
        plateOrderList.forEach(plateOrderToEntity -> orderPlateEntityList.add(orderPlateEntityMapper.toOrderPlateEntity(plateOrderToEntity)));
        orderPlateEntityList.forEach(plateOrderEntity -> plateOrderEntity.setIdOrder(orderRepository.findById(orderEntity.getId()).get().getId()));
        orderPlateRepository.saveAll(orderPlateEntityList);
    }

//    @Override
//    public boolean clientHasOrder(Long id) {
//        boolean orderEntityOptional = orderRepository.findById(id).isPresent();
//        return true;
//    }

    @Override
    public boolean clientHasOrder(Long id) {
        return orderRepository.findByIdClient(id);
    }

//    @Override
//    public Optional<Long> findById(Long id) {
//        Optional<PlateEntity> plateEntityOptional = plateRepository.findById(id);
//        return plateEntityOptional.map(PlateEntity::getId);
//    }
}
