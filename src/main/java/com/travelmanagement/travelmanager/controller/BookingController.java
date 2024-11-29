package com.travelmanagement.travelmanager.controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelmanagement.travelmanager.model.CabBooking;
import com.travelmanagement.travelmanager.model.Response;

import com.travelmanagement.travelmanager.repository.CabBookingRepository;




@Controller
@RequestMapping(path = "/bookcabs")
public class BookingController {

	@Autowired
	CabBookingRepository repository;
	
	
	@PostMapping(path = "/book")
    public ResponseEntity<Response> addBooking(
            @RequestParam String passangerName,
            @RequestParam String pickup,
            @RequestParam String destination,
            @RequestParam LocalDate travelDate,
            @RequestParam LocalTime travelTime,
            @RequestParam String cabtype
            
            ) {
        
        CabBooking cab = new CabBooking(null, passangerName, pickup, destination, travelDate,travelTime, cabtype);
        System.out.println("Cab Booking: " + cab);
        
        try {
            repository.save(cab);
            Response response = new Response(101, "User " + passangerName + " Booked " + cabtype + " Successfully");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
            
        } catch (Exception exception) {
            Response response = new Response(701, "User " + passangerName + " Your Booking Failed. Exception: " + exception.getMessage());
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@GetMapping("/index")
	public String showBookingHistory(Model model) {
	    // Fetch the list of cab bookings
	    List<CabBooking> cabBookings = repository.findAll();

	    // Add the list to the model with the key "cabBookings"
	    model.addAttribute("cabBookings", cabBookings);

	    // Return the Thymeleaf template name
	    return "tripsIndex";
	}
	
    @GetMapping("/bookCab")
    public String bookAcab() {
        return "bookCab";
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<CabBooking> updateCabBooking(@PathVariable Integer id, @RequestBody CabBooking updatedBooking) {
        Optional<CabBooking> optionalCabBooking = repository.findById(id);

        if (optionalCabBooking.isPresent()) {
            CabBooking existingBooking = optionalCabBooking.get();
            existingBooking.setPassengerName(updatedBooking.getPassengerName());
            existingBooking.setPickup(updatedBooking.getPickup());
            existingBooking.setDestination(updatedBooking.getDestination());
            existingBooking.setTravelDate(updatedBooking.getTravelDate());
            existingBooking.setTravelTime(updatedBooking.getTravelTime());
            existingBooking.setCabtype(updatedBooking.getCabtype());
            existingBooking.setCabtype(existingBooking.calcBookingFare()); // Recalculate fare if needed

            CabBooking savedBooking = repository.save(existingBooking);
            return ResponseEntity.ok(savedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCabBooking(@PathVariable Integer id) {
        Optional<CabBooking> optionalCabBooking = repository.findById(id);

        if (optionalCabBooking.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
