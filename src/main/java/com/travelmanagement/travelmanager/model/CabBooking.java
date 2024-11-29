package com.travelmanagement.travelmanager.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cab_bookings")
public class CabBooking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    
    @Column(name = "passenger_name") 
    private String passengerName;
    private String pickup;
    private String destination;
    private LocalDate travelDate;
    private LocalTime travelTime;
    private String cabtype;
    private Double cabFare;

    // Default Constructor
    public CabBooking() {
    }

    // Parameterized Constructor
    public CabBooking(Integer id, String passengerName, String pickup, String destination, LocalDate travelDate,
                      LocalTime travelTime, String cabtype) {
        this.id = id;
        this.passengerName = passengerName;
        this.pickup = pickup;
        this.destination = destination;
        this.travelDate = travelDate;
        this.travelTime = travelTime;
        setCabtype(cabtype); // This ensures the fare is calculated
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }

    public String getCabtype() {
        return cabtype;
    }

    public void setCabtype(String cabtype) {
        if (cabtype == null || cabtype.isEmpty()) {
            throw new IllegalArgumentException("Cab type cannot be null or empty");
        }
        this.cabtype = cabtype;
        this.cabFare = calcBookingFare(); // Recalculate fare when cab type changes
    }

    public Double getCabFare() {
        return cabFare;
    }

    // Calculate Cab Fare
    public double calcBookingFare() {
        if ("SUV".equalsIgnoreCase(cabtype)) {
            return 400.0;
        } else if ("Sedan".equalsIgnoreCase(cabtype)) {
            return 200.0;
        } else {
            return 250.0; // Default fare for other types
        }
    }

    @Override
    public String toString() {
        return "CabBooking [id=" + id + ", passengerName=" + passengerName + ", pickup=" + pickup + ", destination="
                + destination + ", travelDate=" + travelDate + ", travelTime=" + travelTime + ", cabtype=" + cabtype
                + ", cabFare=" + cabFare + "]";
    }

	public void setCabtype(double calcBookingFare) {
		// TODO Auto-generated method stub
		
	}


}
