package uca.dss20192020.hotelproject.cli;

import uca.dss20192020.hotelproject.Model.Room;
import uca.dss20192020.hotelproject.Service.ReservationService;

import java.util.List;
import java.util.Scanner;

public class CheckInCommand implements Command {
    private Long hotelId;
    private Scanner sc = new Scanner(System.in);

    public CheckInCommand(Long hotelId) {
        this.hotelId = hotelId;
    }

    ReservationService reservationService = new ReservationService();
    @Override
    public void execute() {
        System.out.println("Enter your reservation code please");
        String reservationCode = sc.next();
        Long roomId = chooseRoomForCheckIn(hotelId, reservationCode);
        if(roomId!=null)
            reservationService.checkIn(roomId, hotelId, reservationCode);
    }

    private Long chooseRoomForCheckIn(Long hotelId, String reservationCode) {
        List<Room> freeRooms = reservationService.getFreeRoomsForCheckIn(hotelId, reservationCode);
        if(freeRooms.isEmpty())
        {
            System.out.println("NO ROOMS");
            return null;
        }
        System.out.println("Choose one room to check-in ");
        freeRooms.forEach(x ->
                System.out.println("Room number: " + x.getId().toString() + "\n " + x.toString()));

        return sc.nextLong();

    }
}
