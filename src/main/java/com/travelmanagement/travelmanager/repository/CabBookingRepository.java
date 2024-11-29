package com.travelmanagement.travelmanager.repository;




import org.springframework.data.jpa.repository.JpaRepository;


import com.travelmanagement.travelmanager.model.CabBooking;


public interface CabBookingRepository extends JpaRepository<CabBooking, Integer> {
    // No custom methods needed for findAll
}
