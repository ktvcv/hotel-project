package dss.hotelproject.cli;

import dss.hotelproject.cli.Enum.RoomStatus;
import dss.hotelproject.cli.Enum.RoomType;
import dss.hotelproject.cli.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.cli.Model.Hotel;
import dss.hotelproject.cli.Model.Room;
import dss.hotelproject.cli.Service.HotelService;
import dss.hotelproject.cli.Service.RoomService;
import dss.hotelproject.cli.cli.ChoiceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CliApplication {

    public static void main(String[] args) {
        SpringApplication.run(CliApplication.class, args);
    }
    @Autowired
    ChoiceCommand choiceCommand;
    @Bean
    public CommandLineRunner demo(HotelService hotelService,
                                  RoomService roomService) {
        return (args) -> {

            Hotel hotel =  new Hotel.HotelBuilder()
                    .withEmail("some@mail.com")
                    .withName("Hotel")
                    .withCity("City")
                    .withAddress("Calle Sagasta")
                    .withStars(3)
                    .build();

            hotelService.saveHotel(hotel);

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
            choiceCommand.execute();
        };
    }
}
