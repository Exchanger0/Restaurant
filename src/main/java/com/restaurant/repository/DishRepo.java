package com.restaurant.repository;

import com.restaurant.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepo extends ListCrudRepository<Dish, Integer> {

    @Query("from Dish d where d.title LIKE CONCAT('%', :title, '%')")
    List<Dish> findByTitleLike(@Param("title") String title);

    List<Dish> findByType(String type);

    List<Dish> findByPriceLessThanEqual(int price);

    @Query("select distinct type from Dish")
    List<String> getAllTypes();
}
