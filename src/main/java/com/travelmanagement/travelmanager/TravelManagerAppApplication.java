package com.travelmanagement.travelmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackages = "com.travelmanagement.travelmanager.model")
public class TravelManagerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelManagerAppApplication.class, args);
	}

}
