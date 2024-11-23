package com.restaurant.controller;

import com.restaurant.dto.mappers.EmployeeMapper;
import com.restaurant.dto.mappers.RestaurantMapper;
import com.restaurant.model.Employee;
import com.restaurant.model.Restaurant;
import com.restaurant.dto.RestaurantDto;
import com.restaurant.service.EmployeeService;
import com.restaurant.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @Autowired
    public RestaurantController(RestaurantService restaurantService, EmployeeService employeeService,
                                EmployeeMapper employeeMapper) {
        this.restaurantService = restaurantService;
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public String restaurants(Model model) {
        LOGGER.info("Received a GET request to url: /restaurants");

        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", RestaurantMapper.toDtoList(restaurants));
        return "restaurants/index";
    }

    @GetMapping("/{restaurantId}")
    public String showRestaurant(@PathVariable("restaurantId") int restaurantId, Model model) {
        LOGGER.info("Received a GET request to url: /restaurants/{}", restaurantId);

        Restaurant restaurant = restaurantService.findById(restaurantId);
        model.addAttribute("restaurant", RestaurantMapper.toDto(restaurant));
        List<Employee> employees = employeeService.findAllByRestaurantId(restaurantId);
        model.addAttribute("employees", employeeMapper.toDtoList(employees));

        LOGGER.info("Employees size: {}", employees.size());
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


    @PutMapping(value = "/{restaurantId}", consumes = "application/json")
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
