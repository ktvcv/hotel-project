package uca.dss20192020.hotelproject;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uca.dss20192020.hotelproject.Enum.RoomStatus;
import uca.dss20192020.hotelproject.Enum.RoomType;
import uca.dss20192020.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import uca.dss20192020.hotelproject.Model.Guest;
import uca.dss20192020.hotelproject.Model.Hotel;
import uca.dss20192020.hotelproject.Model.Room;
import uca.dss20192020.hotelproject.RepoImpl.HotelRepoImpl;
import uca.dss20192020.hotelproject.cli.ChoiceCommand;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HotelProjectApplication {

	public static void main(String[] args) throws MalformedInputException {
		SpringApplication app = new SpringApplication(HotelProjectApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

		final HotelRepoImpl hotelRepo = new HotelRepoImpl();

		List<Room> roomList = new ArrayList<>();
		List<Guest> guests = new ArrayList<>();

		guests.add(new Guest.GuestBuilder()
				.withDNI("123")
				.withName("viktoriia")
				.withSurname("kotovets")
				.withMobile("909900")
				.build());

		roomList.add(
				new Room.RoomBuilder(1L)
						.withType(RoomType.STANDARD)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.SINGLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(2L)
						.withType(RoomType.STANDARD)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(3L)
						.withType(RoomType.STANDARD)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(4L)
						.withType(RoomType.STANDARD)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(5L)
						.withType(RoomType.STANDARD)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.FAMILY)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(6L)
						.withType(RoomType.STANDARD)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.FAMILY)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(7L)
						.withType(RoomType.BUSINESS)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.SINGLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(8L)
						.withType(RoomType.BUSINESS)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(9L)
						.withType(RoomType.BUSINESS)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		roomList.add(
				new Room.RoomBuilder(10L)
						.withType(RoomType.SUPERIOR)
						.withTypeOfPerson(RoomTypeByMaxNumOfPerson.DOUBLE)
						.withRoomStatus(RoomStatus.FREE)
						.build());
		//creating Hotel instance
		Hotel hotel = new Hotel.HotelBuilder(1L)
				.withEmail("some@mail.com")
				.withName("Hotel")
				.withCity("City")
				.withAddress("Calle Sagasta")
				.withStars(3)
				.withRooms(roomList)
				.withGuests(guests)
				.withReservations(new ArrayList<>())
				.build();
		hotelRepo.saveHotel(hotel);


		ChoiceCommand choiceCommand = new ChoiceCommand();
		choiceCommand.execute();
	}
}

