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
import java.time.LocalDate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class HotelProjectClientWebServiceApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/findRooms",
			params = {"hotel_id", "dateFrom", "dateTo", "numberOfPerson"},
			method = RequestMethod.GET)
	public ResponseEntity<Triple> freeRooms(@RequestParam("hotel_id") String hotel_id,
										   @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
										   @RequestParam("dateTo") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo,
										   @RequestParam("numberOfPerson")  int numberOfPerson) {
		final String url = "http://booking/availableRooms";

		return restTemplate.getForEntity(url,Triple.class,hotel_id,dateFrom, dateTo, numberOfPerson);
	}

	@GetMapping(value = "/welcome/{name}")
	public ResponseEntity<String> freeRooms(@PathVariable String name) {
		final String url = "http://BOOKINGSERVICE/index/"+name;

		return restTemplate.getForEntity(url,String.class);
	}

	@RequestMapping(value = "/reserveRoom",
			params = {"hotel_id", "dateFrom", "dateTo", "numberOfPerson", "roomType", "dni", "confirmationCode", "cardCode", "cardCVC", "cardMonth", "cardYear"},
			method = RequestMethod.POST)
	public ResponseEntity<Reservation> saveReservation(@RequestParam("hotel_id") Long hotel_id,
									   @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
									   @RequestParam("dateTo") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo,
									   @RequestParam("numberOfPerson") int numberOfPerson,
									   @RequestParam("roomType") String roomType,
									   @RequestParam("roomTypeByPerson") String roomTypeByPrice,
									   @RequestParam("dni") String guestDNI,
									   @Valid @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^[0-9]{16}$")
									   @RequestParam("cardCode") String cardCode,
									   @Valid @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^[0-9]{3}$")
									   @RequestParam("cardCVC") String cardCVC,
									   @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^(1[0-2]|[1-9])$")
									   @RequestParam("cardMonth") String cardMonth,
									   @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^[2][0-9]$")
									   @RequestParam("cardYear") String cardYear)
	{
		final String url = "http://booking/reserveRoom";
		ResponseEntity<Reservation> responseEntity = restTemplate.getForEntity(url,Reservation.class,hotel_id,dateFrom, dateTo, numberOfPerson, roomType,roomTypeByPrice, guestDNI,cardCode,cardCVC, cardMonth, cardYear);
		if(responseEntity.getBody() == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return responseEntity;
	}


	public static void main(String[] args) {
		SpringApplication.run(HotelProjectClientWebServiceApplication.class, args);
	}


}
