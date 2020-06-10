package dss.hotelproject.cli;

import dss.hotelproject.Exceptions.NoDataFoundException;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Service.ReservationService;
import dss.hotelproject.Service.RoomService;
import dss.hotelproject.repos.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CheckInCommand implements Command {
    Scanner sc = new Scanner(System.in);

    @Autowired
    ReservationService reservationService;

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationRepo reservationRepo;
    @Override
    public void execute() throws NoDataFoundException {
        System.out.println("Enter your reservation code please");
        String reservationCode = sc.next();
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        System.out.println("Enter hotel id please");
        Long hotelId= sc.nextLong();
        String roomNumber = chooseRoomForCheckIn(hotelId, reservation);
        if(roomNumber!=null)
            reservationService.checkIn(roomNumber, hotelId, reservationCode);
    }

    private String chooseRoomForCheckIn(Long hotelId, Reservation reservation) throws NoDataFoundException {
        List<Room> freeRooms = roomService.getFreeRoomsForCheckIn(hotelId, reservation);
        if(freeRooms.isEmpty())
        {
            throw new NoDataFoundException("No room is free");
        }
        System.out.println("Choose one room to check-in ");
        freeRooms.forEach(x ->
                System.out.println("Room number: " + x.getRoomNumber() + "\n " + x.toString()));

        return sc.next();

    }
}
