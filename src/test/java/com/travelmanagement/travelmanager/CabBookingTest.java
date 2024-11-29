//package com.travelmanagement.travelmanager;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.travelmanagement.travelmanager.model.CabBooking;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CabBookingTest {
//
//    private CabBooking cabBooking;
//
//    @BeforeEach
//    void setUp() {
//        cabBooking = new CabBooking();
//    }
//
//    @Test
//    void testCalcBookingFareSUV() {
//        cabBooking.setCabtype("SUV");
//        assertEquals(400.0, cabBooking.getCabFare(), "The fare for SUV should be 400.0");
//    }
//
//    @Test
//    void testCalcBookingFareSedan() {
//        cabBooking.setCabtype("Sedan");
//        assertEquals(200.0, cabBooking.getCabFare(), "The fare for Sedan should be 200.0");
//    }
//
//    @Test
//    void testCalcBookingFareDefault() {
//        cabBooking.setCabtype("Hatchback");
//        assertEquals(250.0, cabBooking.getCabFare(), "The default fare should be 250.0");
//    }
//
//    @Test
//    void testSetCabtypeValid() {
//        cabBooking.setCabtype("SUV");
//        assertEquals("SUV", cabBooking.getCabtype(), "Cab type should be set to SUV");
//    }
//
//    @Test
//    void testSetCabtypeInvalid() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            cabBooking.setCabtype(null);
//        });
//        assertEquals("Cab type cannot be null or empty", exception.getMessage());
//    }
//
//    @Test
//    void testParameterizedConstructor() {
//        CabBooking booking = new CabBooking(1, "John Doe", "Point A", "Point B", LocalDate.now(), LocalTime.now(), "SUV");
//        assertEquals(1, booking.getId());
//        assertEquals("John Doe", booking.getPassengerName());
//        assertEquals("Point A", booking.getPickup());
//        assertEquals("Point B", booking.getDestination());
//        assertEquals("SUV", booking.getCabtype());
//        assertEquals(400.0, booking.getCabFare(), "The fare for SUV should be 400.0");
//    }
//
//    @Test
//    void testToString() {
//        cabBooking.setId(1);
//        cabBooking.setPassengerName("Alice");
//        cabBooking.setPickup("Home");
//        cabBooking.setDestination("Work");
//        cabBooking.setTravelDate(LocalDate.of(2024, 11, 29));
//        cabBooking.setTravelTime(LocalTime.of(8, 30));
//        cabBooking.setCabtype("Sedan");
//
//        String expected = "CabBooking [id=1, passengerName=Alice, pickup=Home, destination=Work, travelDate=2024-11-29, travelTime=08:30, cabtype=Sedan, cabFare=200.0]";
//        assertEquals(expected, cabBooking.toString());
//    }
//}
