package com.restaurant.repository;

import com.restaurant.model.Employee;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends ListCrudRepository<Employee, Integer> {
    List<Employee> findAllByRestaurantId(int restaurantId);
}
