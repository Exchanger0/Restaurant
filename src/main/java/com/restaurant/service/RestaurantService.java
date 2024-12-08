package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RestaurantService extends BaseService<Restaurant, Integer, RestaurantRepo> {

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo) {
        super(restaurantRepo);
    }

    public Restaurant update(int id, Restaurant restaurant) {
        Restaurant origin = repository.findById(id).orElse(null);
        if (origin != null && restaurant != null) {
            if (restaurant.getImage() != null)
                origin.setImage(restaurant.getImage());
            if (restaurant.getStartTime() != null)
                origin.setStartTime(restaurant.getStartTime());
            if (restaurant.getEndTime() != null)
                origin.setEndTime(restaurant.getEndTime());

            if (restaurant.getAddress() != null) {
                if (restaurant.getAddress().getCountry() != null)
                    origin.getAddress().setCountry(restaurant.getAddress().getCountry());
                if (restaurant.getAddress().getCity() != null)
                    origin.getAddress().setCity(restaurant.getAddress().getCity());
                if (restaurant.getAddress().getStreet() != null)
                    origin.getAddress().setStreet(restaurant.getAddress().getStreet());
                if (restaurant.getAddress().getNumber() != -1)
                    origin.getAddress().setNumber(restaurant.getAddress().getNumber());
            }

            repository.save(origin);
        }
        return origin;
    }
}