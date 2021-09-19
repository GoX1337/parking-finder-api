package com.gox.parking.finder.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ParkingFinderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingFinderApiApplication.class, args);
	}

}
