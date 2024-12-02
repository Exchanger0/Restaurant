package com.restaurant.service;

import com.restaurant.model.Ingredient;
import com.restaurant.repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService extends BaseService<Ingredient, Integer> {

    @Autowired
    public IngredientService(IngredientRepo repository) {
        super(repository);
    }
}
