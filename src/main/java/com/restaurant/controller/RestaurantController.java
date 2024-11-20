package com.restaurant.controller;

import com.restaurant.model.Address;
import com.restaurant.model.Restaurant;
import com.restaurant.dto.RestaurantDto;
import com.restaurant.service.AddressService;
import com.restaurant.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public String createRestaurant(HttpServletRequest request, @ModelAttribute Restaurant restaurant) {
        LOGGER.info("Received a POST request to url: /restaurants");
        LOGGER.info("Save restaurant: {}", restaurant);
        restaurant.setImage(Base64.getDecoder().decode(request.getParameter("image")));
        restaurantService.save(restaurant);
        return "redirect:/restaurants";
    }


    @PatchMapping("/{restaurantId}")
    public String updateRestaurant(@PathVariable("restaurantId") int restaurantId,
                                   @RequestBody MultiValueMap<String, String> map) {
        LOGGER.info("Received a PATH request to url: /restaurants/{}", restaurantId);
        Map<String, String> data = map.toSingleValueMap();

        Address address = new Address(data.get("address.country"), data.get("address.city"), data.get("address.street"),
                Integer.parseInt(data.getOrDefault("address.number", "-1")));
        RestaurantDto restaurantDto = new RestaurantDto(data);
        restaurantDto.setAddress(address);
        Restaurant restaurant = restaurantService.update(restaurantId, restaurantDto);

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
