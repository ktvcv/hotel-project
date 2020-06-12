package dss.hotelproject;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.Model.Hotel;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Payment.CreditCardPayment;
import dss.hotelproject.Service.GuestService;
import dss.hotelproject.Service.HotelService;
import dss.hotelproject.Service.ReservationService;
import dss.hotelproject.Service.RoomService;
import dss.hotelproject.repos.HotelRepo;
import dss.hotelproject.repos.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

@SpringBootApplication
@EnableEurekaClient
public class HotelprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelprojectApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(HotelService hotelService,
								  RoomService roomService,
								  GuestService guestService,
								  ReservationService reservationService) {
		return (args) -> {

			Hotel hotel =  new Hotel.HotelBuilder()
					.withEmail("some@mail.com")
					.withName("Hotel")
					.withCity("City")
					.withAddress("Calle Sagasta")
					.withStars(3)
					.build();

			hotelService.saveHotel(hotel);

			Guest guest = new Guest.GuestBuilder().withDNI("33333333E")
					.withMobile("333333333")
					.withSurname("Kotovets")
					.withMail("kotovec@gmail.com")
					.withName("Viktoriia")
					.build();

			guestService.saveGuest(guest);

			Room room1 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.SINGLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("101")
							.withHotel(hotel)
							.build();
			roomService.saveRoom(room1);

			Room room2 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("102")
							.withHotel(hotel)
							.build();
			roomService.saveRoom(room2);

			Room room3 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("103")
							.withHotel(hotel)
							.build();
			roomService.saveRoom(room3);

			Room room4 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("201")
							.withHotel(hotel)
							.build();
			roomService.saveRoom(room4);

			Room room5 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.FAMILY)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("202")
							.withHotel(hotel)
							.build();

			roomService.saveRoom(room5);
			Room room6 =
					new Room.RoomBuilder()
							.withType(RoomType.STANDARD)
							.withTypeOfPerson(RoomTypeByMaxNumOfPerson.FAMILY)
							.withRoomStatus(RoomStatus.FREE)
							.withRoomNumber("203")
							.withHotel(hotel)
							.build();

			roomService.saveRoom(room6);

		};
	}
}
