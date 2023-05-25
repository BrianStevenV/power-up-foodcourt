//package com.example.foodcourtmicroservice.domain.usecase;
//
//import com.example.foodcourtmicroservice.domain.api.IRestaurantServicePort;
//import com.example.foodcourtmicroservice.domain.model.Restaurant;
//import com.example.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
//
//public class RestaurantUseCase implements IRestaurantServicePort {
//    private final IRestaurantPersistencePort restaurantPersistencePort;
//    public RestaurantUseCase( IRestaurantPersistencePort restaurantPersistencePort){ this.restaurantPersistencePort = restaurantPersistencePort;}
//    @Override
//    public void saveRestaurant(Restaurant restaurant) {
//    //Put validation here, domain logyc.
//        restaurantPersistencePort.saveRestaurant(restaurant);
//    }
//}
