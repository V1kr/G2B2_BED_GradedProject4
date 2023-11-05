package com.vikram.EmployeeManagement.services;

import com.vikram.EmployeeManagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public User createEmployee(String firstName, String lastName, String email) {
        User employee = new User(firstName, lastName, email);
        return employeeRepository.save(employee);
    }

    public List<User> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<User> getAllEmployeesAsc() {
        Sort sort = Sort.by(Sort.Order.asc("firstName"));
        return employeeRepository.findAll(sort);
    }

    public List<User> getAllEmployeesDesc() {
        Sort sort = Sort.by(Sort.Order.desc("firstName"));
        return employeeRepository.findAll(sort);
    }

    public User getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public User updateEmployee(Long id, String firstName, String lastName, String email) {
        User existingUser = employeeRepository.findById(id).orElse(null);
        if (existingUser != null) {
            // Update fields as needed
            existingUser.setFirstName(firstName);
            existingUser.setLastName(lastName);
            existingUser.setEmail(email);
            // Save the updated ticket
            return employeeRepository.save(existingUser);
        }
        return null; // Handle not found case
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<User> searchUsers(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }
}
