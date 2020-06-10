package dss.hotelproject;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.Model.Hotel;
import dss.hotelproject.Model.Room;
import dss.hotelproject.repos.HotelRepo;
import dss.hotelproject.repos.RoomRepo;
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

	@Bean
	public CommandLineRunner demo(HotelRepo hotelRepo,
								  RoomRepo roomRepo) {
		return (args) -> {

			Hotel hotel =  new Hotel.HotelBuilder()
					.withEmail("some@mail.com")
					.withName("Hotel")
					.withCity("City")
					.withAddress("Calle Sagasta")
					.withStars(3)
					.build();

			hotelRepo.save(hotel);

			Room room1 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.SINGLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("101")
							.withHotel(hotel)
							.build();
			roomRepo.save(room1);

			Room room2 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("102")
							.withHotel(hotel)
							.build();
			roomRepo.save(room2);

			Room room3 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("103")
							.withHotel(hotel)
							.build();
			roomRepo.save(room3);

			Room room4 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("201")
							.withHotel(hotel)
							.build();
			roomRepo.save(room4);

			Room room5 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.FAMILY)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("202")
							.withHotel(hotel)
							.build();

			roomRepo.save(room5);
			Room room6 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.FAMILY)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("203")
							.withHotel(hotel)
							.build();

			roomRepo.save(room6);

			System.out.println(roomRepo.findAll());
			System.out.println(hotelRepo.findAll());

		};
	}
}
