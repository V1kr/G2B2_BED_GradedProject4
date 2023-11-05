package com.vikram.EmployeeManagement.controllers;

import com.vikram.EmployeeManagement.models.User;
import com.vikram.EmployeeManagement.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService; // Assuming you have a TicketService class

    // Display a list of tickets
    @GetMapping("/api/employees")
    public String listEmployees(@RequestParam(name = "order", required = false) String order) {
//        System.out.println(order);
        List<User> users = null;
        if(order == null){
            users = employeeService.getAllEmployees();
        }
        else if(order.equals("asc")){
             users = employeeService.getAllEmployeesAsc();
        }
        else if(order.equals("desc")){
            users = employeeService.getAllEmployeesDesc();
        }

        String employees = "{";
        for(Iterator<User> i = users.iterator(); i.hasNext();){
            User u = i.next();
            employees += "{";
            employees += "id: " + u.getId() + ", ";
            employees += "firstName: " + u.getFirstName() + ", ";
            employees += "lastName: " + u.getLastName() + ", ";
            employees += "email: " + u.getEmail() + "}";
            if(i.hasNext())
                employees += ", ";
        }
        employees += "}";
        return employees + " " + order;
    }
//
//    // Display a form to create a new ticket
//    @GetMapping("/create")
//    public String createTicketForm(Model model) {
//        model.addAttribute("ticket", new Ticket());
//        return "create"; // Use the appropriate Thymeleaf template for creating tickets
//    }

    // Handle the form submission to create a new ticket
    @PostMapping("/api/create")
    public String createTicketSubmit(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        employeeService.createEmployee(firstName, lastName, email);
        return "Successfully created employee";
    }

    // Display details of a specific ticket
    @GetMapping("/api/employees/{id}")
    public String viewEmployee(@PathVariable Long id) {
        User user = employeeService.getEmployeeById(id);
        if (user == null) {
            return "Employee does not exist"; // Redirect to the ticket list page if the ticket doesn't exist
        }
        String employee = "{ "+"id: " + user.getId() + ", firstName: " + user.getFirstName() + ", lastName: " + user.getLastName()+ ", email: " + user.getEmail() +" }";
        return employee; // Use the appropriate Thymeleaf template for viewing a ticket
    }

//    // Display a form to edit a specific ticket
//    @GMapping("/{id}/edit")
//    public String editTicketForm(@PathVariable Long id, Model model) {
//        Ticket ticket = employeeService.getTicketById(id);
//        if (ticket == null) {
//            return "redirect:/ticket"; // Redirect to the ticket list page if the ticket doesn't exist
//        }
//        model.addAttribute("ticket", ticket);
//        return "edit"; // Use the appropriate Thymeleaf template for editing a ticket
//    }

    // Handle the form submission to update a specific ticket
    @PostMapping("/api/employees/{id}/edit")
    public String editTicketSubmit(@PathVariable Long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        employeeService.updateEmployee(id, firstName, lastName, email);
        return "successfully updated employee"; // Redirect to the ticket list page
    }

    // Delete a specific ticket
    @PostMapping("/api/employees/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "successfully deleted employee"; // Redirect to the ticket list page
    }

    @PostMapping("/api/employees/search/{firstName}")
    public String searchTickets(@PathVariable String firstName) {
        List<User> searchResults = employeeService.searchUsers(firstName);
        String employees = "{";
        for(Iterator<User> i = searchResults.iterator(); i.hasNext();){
            User u = i.next();
            employees += "{";
            employees += "id: " + u.getId() + ", ";
            employees += "firstName: " + u.getFirstName() + ", ";
            employees += "lastName: " + u.getLastName() + ", ";
            employees += "email: " + u.getEmail() + "}";
            if(i.hasNext())
                employees += ", ";
        }
        employees += "}";
        return employees;
    }
}
