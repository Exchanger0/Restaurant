package com.restaurant.controller;

import com.restaurant.model.Restaurant;
import com.restaurant.service.AddressService;
import com.restaurant.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
