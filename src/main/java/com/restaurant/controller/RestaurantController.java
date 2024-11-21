package com.restaurant.controller;

import com.restaurant.dto.mappers.RestaurantMapper;
import com.restaurant.model.Restaurant;
import com.restaurant.dto.RestaurantDto;
import com.restaurant.service.AddressService;
import com.restaurant.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final AddressService addressService;

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @Autowired
    public RestaurantController(RestaurantService restaurantService, AddressService addressService) {
        this.restaurantService = restaurantService;
        this.addressService = addressService;
    }

    @GetMapping
    public String restaurants(Model model) {
        LOGGER.info("Received a GET request to url: /restaurants");

        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("images", restaurantService.getImagesBase64(restaurants));
        return "restaurants/index";
    }

    @GetMapping("/{restaurantId}")
    public String showRestaurant(@PathVariable("restaurantId") int restaurantId, Model model) {
        LOGGER.info("Received a GET request to url: /restaurants/{}", restaurantId);

        Restaurant restaurant = restaurantService.findById(restaurantId);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("image", Base64.getEncoder().encodeToString(restaurant.getImage()));

        LOGGER.info("Show: {}", restaurant);
        return "restaurants/show";
    }

    @PostMapping(consumes = "application/json")
    public String createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        LOGGER.info("Received a POST request to url: /restaurants");
        LOGGER.info("Save restaurant: {}", restaurantDto);

        restaurantService.save(RestaurantMapper.fromDto(restaurantDto));
        return "redirect:/restaurants";
    }


    @PatchMapping(value = "/{restaurantId}", consumes = "application/json")
    public String updateRestaurant(@PathVariable("restaurantId") int restaurantId,
                                   @RequestBody RestaurantDto restaurantDto) {
        LOGGER.info("Received a PATH request to url: /restaurants/{}", restaurantId);

        Restaurant restaurant = restaurantService.update(restaurantId, RestaurantMapper.fromDto(restaurantDto));

        LOGGER.info("Was updated: {}", restaurant);
        return "redirect:/restaurants";
    }

    @DeleteMapping("/{restaurantId}")
    public String deleteRestaurant(@PathVariable("restaurantId") int restaurantId) {
        LOGGER.info("Received a DELETE request to url: /restaurants/{}", restaurantId);

        Restaurant restaurant = restaurantService.findById(restaurantId);
        if (restaurant != null)
            restaurantService.delete(restaurant);

        LOGGER.info("Delete restaurant: {}", restaurant);

        return "redirect:/restaurants";
    }

}
