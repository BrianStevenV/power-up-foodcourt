package com.example.foodcourtmicroservice.configuration;

import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.CategoryMysqlAdapter;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.RestaurantMysqlAdapter;
import com.example.foodcourtmicroservice.domain.api.IRestaurantServicePort;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
import com.example.foodcourtmicroservice.domain.usecase.FeignClientRestaurantUseCase;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.PlateMysqlAdapter;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.RestaurantFeignClient;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.adapter.FeignRestaurantMysqlAdapter;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;
import com.example.foodcourtmicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.example.foodcourtmicroservice.domain.api.IPlateServicePort;
import com.example.foodcourtmicroservice.domain.api.IRestaurantExternalServicePort;
import com.example.foodcourtmicroservice.domain.spi.ICategoryPersistencePort;
import com.example.foodcourtmicroservice.domain.spi.IPlatePersistencePort;
import com.example.foodcourtmicroservice.domain.spi.IRestaurantExternalPersistencePort;
import com.example.foodcourtmicroservice.domain.usecase.PlateUseCase;
import com.example.foodcourtmicroservice.domain.usecase.RestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    //Restaurant
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    //Feign client
    private final RestaurantFeignClient restaurantFeignClient;

    //Plate
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;

    //Category
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantMysqlAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryMysqlAdapter(categoryRepository, categoryEntityMapper);
    }

    //No falta service port?

    @Bean
    public IPlatePersistencePort platePersistencePort() {
        return new PlateMysqlAdapter(plateRepository, plateEntityMapper);
    }

    @Bean
    public IPlateServicePort plateServicePort() {
        return new PlateUseCase(platePersistencePort(), categoryPersistencePort());
    }

    @Bean
    public IRestaurantExternalPersistencePort restaurantExternalPersistencePort() {
        return new FeignRestaurantMysqlAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantExternalServicePort restaurantExternalServicePort() {
        return new FeignClientRestaurantUseCase(restaurantFeignClient, restaurantExternalPersistencePort());
    }

    @Bean
    public FeignClientRestaurantUseCase feignClientRestaurantAdapter() {
        return new FeignClientRestaurantUseCase(restaurantFeignClient, restaurantExternalPersistencePort());
    }


}
