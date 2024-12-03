package com.restaurant.service;

import com.restaurant.model.Dish;
import com.restaurant.repository.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DishService extends BaseService<Dish, Integer> {

    @Autowired
    public DishService(DishRepo dishRepo) {
        super(dishRepo);
    }

    public Dish update(int id, Dish dish) {
        Dish origin = repository.findById(id).orElse(null);
        if (origin != null && dish != null) {
            if (dish.getTitle() != null && !dish.getTitle().isEmpty())
                origin.setTitle(dish.getTitle());
            if (dish.getDescription() != null && !dish.getDescription().isEmpty())
                origin.setDescription(dish.getDescription());
            if (dish.getImage() != null && dish.getImage().length != 0)
                origin.setImage(dish.getImage());
            if (dish.getPrice() != -1)
                origin.setPrice(dish.getPrice());
            if (dish.getType() != null && !dish.getType().isEmpty())
                origin.setType(dish.getType());
            if (dish.getIngredients() != null)
                origin.setIngredients(dish.getIngredients());
            save(origin);
        }
        return origin;
    }
}
