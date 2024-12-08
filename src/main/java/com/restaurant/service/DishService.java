package com.restaurant.service;

import com.restaurant.model.Dish;
import com.restaurant.repository.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DishService extends BaseService<Dish, Integer, DishRepo> {

    @Autowired
    public DishService(DishRepo dishRepo) {
        super(dishRepo);
    }

    public Dish update(int id, Dish dish) {
        Dish origin = repository.findById(id).orElse(null);
        if (origin != null && dish != null) {
            if (dish.getTitle() != null && !dish.getTitle().isEmpty())
                origin.setTitle(dish.getTitle());
            if (dish.getDescription() != null && !dish.getDescription().isEmpty())
                origin.setDescription(dish.getDescription());
            if (dish.getImage() != null && dish.getImage().length != 0)
                origin.setImage(dish.getImage());
            if (dish.getPrice() != -1)
                origin.setPrice(dish.getPrice());
            if (dish.getType() != null && !dish.getType().isEmpty())
                origin.setType(dish.getType());
            if (dish.getIngredients() != null)
                origin.setIngredients(dish.getIngredients());
            save(origin);
        }
        return origin;
    }

    public List<Dish> findByPredict(String type, String price, String title) {
        Set<Dish> byType = new HashSet<>();
        Set<Dish> byPrice = new HashSet<>();
        Set<Dish> byTitle = new HashSet<>();

        if (!type.equals("*"))
            byType.addAll(findByType(type));

        if (!price.equals("*")) {
            try {
                int priceInt = Integer.parseInt(price);
                byPrice.addAll(findByPriceLessThanEqual(priceInt));
            }catch (NumberFormatException ignored) {}
        }

        if (!title.equals("*"))
            byTitle.addAll(findByTitleLike(title));

        Set<Dish> all = new HashSet<>();

        if (!byType.isEmpty())
            all.addAll(byType);
        if (!byPrice.isEmpty())
            if (all.isEmpty())
                all.addAll(byPrice);
            else
                all.retainAll(byPrice);
        if (!byTitle.isEmpty())
            if (all.isEmpty())
                all.addAll(byTitle);
            else
                all.retainAll(byTitle);

        return new ArrayList<>(all);
    }

    public List<Dish> findByType(String type) {
        return repository.findByType(type);
    }

    public List<Dish> findByPriceLessThanEqual(int price) {
        return repository.findByPriceLessThanEqual(price);
    }

    public List<Dish> findByTitleLike(String title) {
        return repository.findByTitleLike(title);
    }

    public List<String> getAllTypes() {
        return repository.getAllTypes();
    }
}
