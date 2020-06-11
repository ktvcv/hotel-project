package uca.dss20192020.hotelproject.cli;


import uca.dss20192020.hotelproject.Service.ReservationService;

import java.util.Scanner;

public class CheckOutCommand implements Command {

   private final ReservationService reservationService = new ReservationService();
   private Long hotelId;
    private Scanner sc = new Scanner(System.in);


    public CheckOutCommand(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public void execute() {
        System.out.println("Enter your reservation code please");
        String reservationCode = sc.next();
        reservationService.checkOut(hotelId,reservationCode);
    }
}
