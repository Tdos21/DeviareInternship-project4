package com.travelmanagement.travelmanager.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelmanagement.travelmanager.model.Admin;

import com.travelmanagement.travelmanager.model.Response;
import com.travelmanagement.travelmanager.repository.AdminRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/admins")
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@PostMapping(path = "/add")
    public ResponseEntity<Response> addBooking(
            @RequestParam String username,
            @RequestParam String password
            ) {
        
        Admin admin = new Admin(null, username, password);
        System.out.println("Cab Booking: " + admin);
        
        try {
            adminRepository.save(admin);
            Response response = new Response(101, "Admin " + username+ " created successfully");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
            
        } catch (Exception exception) {
            Response response = new Response(701, "Admin " + username + " Your Booking Failed. Exception: " + exception.getMessage());
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	   // Handle the login form submission
    @PostMapping(path = "/loginRequest")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        // Retrieve admin from the database
        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            return "adminDashboard"; // Redirect to admin dashboard
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login"; // Reload login page with error message
        }
    }
    
    @GetMapping("/login")
    public String login() {
        return "adminLogin";
    }
    
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

}
