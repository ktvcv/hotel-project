package uca.dss20192020.hotelproject.cli;

import uca.dss20192020.hotelproject.Model.Reservation;
import uca.dss20192020.hotelproject.RepoImpl.ReservationRepoImpl;
import uca.dss20192020.hotelproject.Service.ReservationService;

import java.util.Scanner;

public class CancelCommand implements Command {
    final private ReservationService reservationService= new ReservationService();
    private final Long hotelId;
    private final ReservationRepoImpl reservationRepo = new ReservationRepoImpl();
    private final Scanner sc = new Scanner(System.in);

    public CancelCommand(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public void execute() {
        System.out.println("Enter your reservation code please");
        String reservationCodeCancel = sc.next();
        Reservation reservation = reservationRepo.getReservationByReservationCode(hotelId,reservationCodeCancel);
        double charge = reservationService.cancelReservation(hotelId, reservation);
        if(charge > -1)
        {
            System.out.println("Charge for cancellation is: " + charge);
        }
    }
}
