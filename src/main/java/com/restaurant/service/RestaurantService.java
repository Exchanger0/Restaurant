package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.dto.RestaurantDto;
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

    public Restaurant update(int id, RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant != null) {
            if (restaurantDto.getImage() != null)
                restaurant.setImage(restaurantDto.getImage());
            if (restaurantDto.getStartTime() != null)
                restaurant.setStartTime(restaurantDto.getStartTime());
            if (restaurantDto.getEndTime() != null)
                restaurant.setEndTime(restaurantDto.getEndTime());

            if (restaurantDto.getAddress() != null) {
                if (restaurantDto.getAddress().getCountry() != null)
                    restaurant.getAddress().setCountry(restaurantDto.getAddress().getCountry());
                if (restaurantDto.getAddress().getCity() != null)
                    restaurant.getAddress().setCity(restaurantDto.getAddress().getCity());
                if (restaurantDto.getAddress().getStreet() != null)
                    restaurant.getAddress().setStreet(restaurantDto.getAddress().getStreet());
                if (restaurantDto.getAddress().getNumber() != -1)
                    restaurant.getAddress().setNumber(restaurantDto.getAddress().getNumber());
            }

            restaurantRepo.save(restaurant);
        }
        return restaurant;
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

    public void deleteAll(Iterable<Restaurant> Restaurantes) {
        restaurantRepo.deleteAll(Restaurantes);
    }

    public List<String> getImagesBase64(List<Restaurant> restaurants) {
        return restaurants.stream().map(restaurant -> Base64.getEncoder().encodeToString(restaurant.getImage())).toList();
    }
}