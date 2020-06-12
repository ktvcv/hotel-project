package com.uca.dss20192020.hotelprojectclientwebservice;

import com.uca.dss20192020.hotelprojectclientwebservice.Model.Reservation;
import com.uca.dss20192020.hotelprojectclientwebservice.Model.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
public class HotelProjectClientWebServiceApplication {

	@Autowired
	ServiceWeb serviceWeb;

	@GetMapping(value = "/welcome/{name}")
	public ResponseEntity<String> freeRooms(@PathVariable String name) {
		return serviceWeb.getGreeting(name);
	}

	@RequestMapping(value = "/findRooms",
			params = {"hotel_id", "dateFrom", "dateTo", "numberOfPerson"},
			method = RequestMethod.GET)
	public Triple[] freeRooms(@RequestParam("hotel_id") Long hotel_id,
								  @RequestParam("dateFrom") String dateFrom,
								  @RequestParam("dateTo") String dateTo,
								  @RequestParam("numberOfPerson")  int numberOfPerson) {

		return serviceWeb.freeRooms(hotel_id,dateFrom,dateTo,numberOfPerson);
	}

	@GetMapping(value = "/allReservations/{hotel_id}")
	public Reservation[] getAllReservation(@PathVariable Long hotel_id)
	{
		return serviceWeb.getAllReservation(hotel_id);
	}
	public static void main(String[] args) {
		SpringApplication.run(HotelProjectClientWebServiceApplication.class, args);
	}



}
