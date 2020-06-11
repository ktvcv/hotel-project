package dss.hotelproject;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.Model.Hotel;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Service.ExtrasService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class HotelprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelprojectApplication.class, args);
	}

}
