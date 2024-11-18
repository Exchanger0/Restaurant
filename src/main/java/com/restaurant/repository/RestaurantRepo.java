package com.restaurant.repository;

import com.restaurant.model.Restaurant;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends ListCrudRepository<Restaurant, Integer> {
}
