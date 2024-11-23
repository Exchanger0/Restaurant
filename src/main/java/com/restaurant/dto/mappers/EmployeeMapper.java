package com.restaurant.dto.mappers;

import com.restaurant.dto.EmployeeDto;
import com.restaurant.dto.RestaurantDto;
import com.restaurant.model.Employee;
import com.restaurant.model.Restaurant;
import com.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class EmployeeMapper {
    @Autowired
    private RestaurantService restaurantService;

    public Employee fromDto(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary(),
                employeeDto.getPosition(), null, restaurantService.findById(employeeDto.getRestaurantId()));
        if (employeeDto.getPhoto() != null && !employeeDto.getPhoto().isEmpty())
            employee.setPhoto(Base64.getDecoder().decode(employeeDto.getPhoto()));
        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), employee.getSalary(),
                employee.getPosition(), null, employee.getRestaurant().getId());
        employeeDto.setId(employee.getId());
        if (employee.getPhoto() != null && employee.getPhoto().length != 0)
            employeeDto.setPhoto(Base64.getEncoder().encodeToString(employee.getPhoto()));
        return employeeDto;
    }

    public List<EmployeeDto> toDtoList(List<Employee> restaurants) {
        return restaurants.stream().map(this::toDto).toList();
    }
}
