package com.restaurant.repository;

import com.restaurant.model.Ingredient;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends ListCrudRepository<Ingredient, Integer> {
}
