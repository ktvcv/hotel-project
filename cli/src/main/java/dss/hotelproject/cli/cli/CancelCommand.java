package dss.hotelproject.cli.cli;


import dss.hotelproject.cli.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CancelCommand implements Command {
    @Autowired
    private ReservationService reservationService;
    Scanner sc = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Enter your reservation code please");
        String reservationCodeCancel = sc.next();
        System.out.println("Enter hotel id please");
        Long hotelId = sc.nextLong();
        reservationService.cancelReservation(hotelId, reservationCodeCancel);
    }
}
