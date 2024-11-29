package com.travelmanagement.travelmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelmanagement.travelmanager.model.CabBooking;
import com.travelmanagement.travelmanager.model.Response;
import com.travelmanagement.travelmanager.model.User;
import com.travelmanagement.travelmanager.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    
    @Autowired
    UserRepository repository;
    
    @PostMapping(path = "/add")
    public ResponseEntity<Response> addUser(
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String address,
            @RequestParam String zipCode) {
        
        User user = new User(null, name, lastName, phone, email,password, address,zipCode);
        System.out.println("User: " + user);
        
        try {
            repository.save(user);
            Response response = new Response(101, "User " + name + " Saved Successfully");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
            
        } catch (Exception exception) {
            Response response = new Response(701, "User " + name + " Not Saved Successfully. Exception: " + exception.getMessage());
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/loginRequest")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        // Fetch user by email
        User user = repository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // If credentials are valid, add user to the model to show on the next page
            model.addAttribute("loggedInUser", user);

            // Redirect to the client dashboard or home page
            return "clientDashboard"; // Replace with your target page
        }

        // Invalid credentials, add error message to the model
        model.addAttribute("error", "Invalid email or password");

        // Redirect back to the login page
        return "login"; // Replace with your login page template name
    }

    @GetMapping("/clientDashboard")
    public String showClientDashboard(Model model, @RequestParam(required = false) String email) {
        if (email == null) {
            return "redirect:/login"; // Redirect to login if email is not provided
        }

        User user = repository.findByEmail(email);
        if (user == null) {
            return "redirect:/login"; // Redirect to login if user not found
        }

        model.addAttribute("loggedInUser", user);
        return "clientDashboard"; // Display the dashboard
    }



    
    @GetMapping("/index")
    public String showAllUsers(Model model) {
        // Fetch all users from the database
        List<User> userList = repository.findAll();

        // Add the list of users to the model
        model.addAttribute("users", userList);

        // Return the name of the template to display the list of users
        return "usersIndex"; // Replace with the name of your Thymeleaf template or HTML page
    }
    
    
    @GetMapping("/logout")
    public String logout() {
        // Invalidate the session
       

        // Redirect to the index page
        return "clientHome";
    }
    
    
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        // Find the user by ID
        User user = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        // Add user to the model to pre-populate the form
        model.addAttribute("user", user);
        
        return "editUser"; // Replace with the name of your edit form template
    }

    // Method to update the user details
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user) {
        // Save the updated user to the database
        repository.save(user);
        
        return "redirect:/users"; // Redirect to the list of users after updating
    }

    // Method to delete a user
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        // Find and delete the user by ID
        repository.deleteById(id);

        return "redirect:/users"; // Redirect to the list of users after deletion
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/createUser")
    public String create() {
        return "createAcc";
    }
}
