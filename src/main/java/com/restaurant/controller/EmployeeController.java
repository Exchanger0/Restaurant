package com.restaurant.controller;

import com.restaurant.dto.EmployeeDto;
import com.restaurant.dto.mappers.EmployeeMapper;
import com.restaurant.model.Employee;
import com.restaurant.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);


    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeDto> getEmployees() {
        LOGGER.info("Received a GET request to url: /employees");

        return employeeMapper.toDtoList(employeeService.findAll());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") int employeeId) {
        LOGGER.info("Received a GET request to url: /employees/{}", employeeId);

        Employee employee = employeeService.findById(employeeId);
        if (employee != null)
            return ResponseEntity.ok(employeeMapper.toDto(employee));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employeeDto) {
        LOGGER.info("Received a POST request to url: /employees");

        System.out.println(employeeDto);
        Employee employee = employeeService.save(employeeMapper.fromDto(employeeDto));

        LOGGER.info("Success create employee: {}", employee);
        return employeeMapper.toDto(employee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> delete(@PathVariable("employeeId") int employeeId) {
        LOGGER.info("Received a DELETE request to url: /employees/{}", employeeId);
        employeeService.deleteById(employeeId);
        LOGGER.info("Delete employee with id = {}", employeeId);
        return ResponseEntity.ok("success delete");
    }

    @PutMapping("/{employeeId}")
    public EmployeeDto update(@PathVariable("employeeId") int employeeId, @RequestBody EmployeeDto employeeDto)  {
        LOGGER.info("Received a PATH request to url: /employees/{}", employeeId);

        Employee employee = employeeService.update(employeeId, employeeMapper.fromDto(employeeDto));

        LOGGER.info("Was updated: {}", employee);
        return employeeMapper.toDto(employee);
    }
}
