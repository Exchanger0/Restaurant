package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Autowired

    public RestaurantService(RestaurantRepo RestaurantRepo) {
        this.restaurantRepo = RestaurantRepo;
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    public List<Restaurant> saveAll(Iterable<Restaurant> entities) {
        return restaurantRepo.saveAll(entities);
    }

    public Restaurant update(int id, Restaurant restaurant) {
        Restaurant origin = restaurantRepo.findById(id).orElse(null);
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

            restaurantRepo.save(origin);
        }
        return origin;
    }

    @Transactional(readOnly = true)
    public Restaurant findById(int id) {
        return restaurantRepo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public boolean existsById(int id) {
        return restaurantRepo.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<Restaurant> findAll() {
        return restaurantRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Restaurant> findAllById(Iterable<Integer> ids) {
        return restaurantRepo.findAllById(ids);
    }

    @Transactional(readOnly = true)
    public long count() {
        return restaurantRepo.count();
    }

    public void deleteById(int id) {
        restaurantRepo.deleteById(id);
    }

    public void delete(Restaurant restaurant) {
        restaurantRepo.delete(restaurant);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        restaurantRepo.deleteAllById(ids);
    }

    public void deleteAll(Iterable<Restaurant> restaurants) {
        restaurantRepo.deleteAll(restaurants);
    }

    public List<String> getImagesBase64(List<Restaurant> restaurants) {
        return restaurants.stream().map(restaurant -> Base64.getEncoder().encodeToString(restaurant.getImage())).toList();
    }
}