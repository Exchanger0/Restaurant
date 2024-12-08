package com.restaurant.service;

import com.restaurant.model.Employee;
import com.restaurant.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService extends BaseService<Employee, Integer, EmployeeRepo> {

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        super(employeeRepo);
    }

    public List<Employee> findAllByRestaurantId(int restaurantId) {
        return repository.findAllByRestaurantId(restaurantId);
    }

    public Employee update(int id, Employee employee) {
        Employee origin = repository.findById(id).orElse(null);
        if (origin != null && employee != null) {
            if (employee.getFirstName() != null && !employee.getFirstName().isEmpty())
                origin.setFirstName(employee.getFirstName());
            if (employee.getLastName() != null && !employee.getLastName().isEmpty())
                origin.setLastName(employee.getLastName());
            if (employee.getSalary() != -1)
                origin.setSalary(employee.getSalary());
            if (employee.getPosition() != null && !employee.getPosition().isEmpty())
                origin.setPosition(employee.getPosition());
            if (employee.getPhoto() != null && employee.getPhoto().length != 0)
                origin.setPhoto(employee.getPhoto());
            if (employee.getRestaurant() != null)
                origin.setRestaurant(employee.getRestaurant());

            repository.save(origin);
        }

        return origin;
    }
}
