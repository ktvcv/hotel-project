package com.uca.dss20192020.hotelprojectclientwebservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.uca.dss20192020.hotelprojectclientwebservice.Model.Reservation;
import com.uca.dss20192020.hotelprojectclientwebservice.Model.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Service
public class ServiceWeb {
    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultMessage")
    public ResponseEntity<String> getGreeting(String name) {
        final String url = "http://BOOKINGSERVICE/index/"+name;

        return restTemplate.getForEntity(url,String.class);
    }

    private ResponseEntity<String> defaultMessage(String name) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Default message");
    }

    @HystrixCommand(fallbackMethod = "defaultFreeRooms")
    public Triple[] freeRooms(Long hotel_id,
                            String dateFrom,
                            String dateTo,
                            int numberOfPerson) {
        final String url = "http://BOOKINGSERVICE/availableRooms?dateFrom={dateFrom}&dateTo={dateTo}&hotel_id={hotel_id}&numberOfPerson={numberOfPerson}";
        return restTemplate.getForEntity(url, Triple[].class, dateFrom, dateTo, hotel_id, numberOfPerson).getBody();

    }

    private Triple[] defaultFreeRooms(Long hotel_id,
                                    String dateFrom,
                                    String dateTo,
                                    int numberOfPerson) {
        return null;
    }

    @HystrixCommand(fallbackMethod = "defaultAllReservation")
    public Reservation[] getAllReservation(Long hotel_id)
    {
        final String url = "http://BOOKINGSERVICE/allReservations/"+hotel_id;
        return restTemplate.getForEntity(url,Reservation[].class).getBody();
    }

    private Reservation[] defaultAllReservation(Long hotel_id) {
        return null;
    }


}

