package com.restaurant.dto.mappers;

import com.restaurant.dto.DishDto;
import com.restaurant.model.Dish;

import java.util.Base64;
import java.util.List;

public class DishMapper {

    public static Dish fromDto(DishDto dishDto) {
        Dish dish = new Dish(dishDto.getTitle(), dishDto.getDescription(), null, dishDto.getPrice(),
                dishDto.getType(), dishDto.getIngredients());
        if (dishDto.getImage() != null && !dishDto.getImage().isEmpty())
            dish.setImage(Base64.getDecoder().decode(dishDto.getImage()));
        return dish;
    }

    public static DishDto toDto(Dish dish) {
        DishDto dishDto = new DishDto(dish.getTitle(), dish.getDescription(), null, dish.getPrice(),
                dish.getType(), dish.getIngredients());
        dishDto.setId(dish.getId());
        if (dish.getImage() != null && dish.getImage().length != 0)
            dishDto.setImage(Base64.getEncoder().encodeToString(dish.getImage()));
        return dishDto;
    }

    public static List<DishDto> toDtoList(List<Dish> dishes) {
        return dishes.stream().map(DishMapper::toDto).toList();
    }
}
