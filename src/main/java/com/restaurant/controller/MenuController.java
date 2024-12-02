package com.restaurant.controller;

import com.restaurant.dto.DishDto;
import com.restaurant.dto.mappers.DishMapper;
import com.restaurant.model.Dish;
import com.restaurant.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private final DishService dishService;

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @Autowired
    public MenuController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public String dishes(Model model) {
        LOGGER.info("Received a GET request to url: /menu");

        List<Dish> dishes = dishService.findAll();
        model.addAttribute("dishes", DishMapper.toDtoList(dishes));
        return "menu/index";
    }


    @GetMapping("/{dishId}")
    public String showDish(@PathVariable("dishId") int dishId, Model model) {
        LOGGER.info("Received a GET request to url: /menu/{}", dishId);

        Dish dish = dishService.findById(dishId);
        System.out.println(dish);
        model.addAttribute("dish", DishMapper.toDto(dish));

        LOGGER.info("Show: {}", dish);
        LOGGER.info("Ingredients: {}", dish.getIngredients());
        return "menu/show";
    }

    @PostMapping(consumes = "application/json")
    public String createDish(@RequestBody DishDto dish) {
        LOGGER.info("Received a POST request to url: /menu");
        LOGGER.info("Save dish: {}", dish);

        dishService.save(DishMapper.fromDto(dish));
        return "redirect:/menu";
    }

    @PutMapping(value = "/{dishId}", consumes = "application/json")
    public String updateRestaurant(@PathVariable("dishId") int dishId,
                                   @RequestBody DishDto dishDto) {
        LOGGER.info("Received a PUT request to url: /menu/{}", dishId);

        Dish dish = dishService.update(dishId, DishMapper.fromDto(dishDto));

        LOGGER.info("Was updated: {}", dish);
        return "redirect:/menu";
    }

    @DeleteMapping("/{dishId}")
    public String deleteRestaurant(@PathVariable("dishId") int dishId) {
        LOGGER.info("Received a DELETE request to url: /menu/{}", dishId);

        Dish dish = dishService.findById(dishId);
        if (dish != null)
            dishService.delete(dish);

        LOGGER.info("Delete dish: {}", dish);

        return "redirect:/menu";
    }
}
