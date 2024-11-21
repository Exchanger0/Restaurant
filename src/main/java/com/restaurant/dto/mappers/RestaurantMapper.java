package com.restaurant.dto.mappers;

import com.restaurant.dto.RestaurantDto;
import com.restaurant.model.Restaurant;

import java.util.Base64;

public class RestaurantMapper {

    public static Restaurant fromDto(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant(null, restaurantDto.getAddress(),
                restaurantDto.getStartTime(), restaurantDto.getEndTime());
        if (restaurantDto.getImage() != null && !restaurantDto.getImage().isEmpty())
            restaurant.setImage(Base64.getDecoder().decode(restaurantDto.getImage()));
        return restaurant;
    }

    public static RestaurantDto toDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto("", restaurant.getAddress(),
                restaurant.getStartTime(), restaurant.getEndTime());
        if (restaurant.getImage() != null && restaurant.getImage().length != 0)
            restaurantDto.setImage(Base64.getEncoder().encodeToString(restaurant.getImage()));
        return restaurantDto;
    }
}
