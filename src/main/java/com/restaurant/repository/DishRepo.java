package com.restaurant.repository;

import com.restaurant.model.Dish;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepo extends ListCrudRepository<Dish, Integer> {
}
