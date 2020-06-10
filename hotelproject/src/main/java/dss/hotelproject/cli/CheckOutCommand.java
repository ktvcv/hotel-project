package dss.hotelproject.cli;


import dss.hotelproject.Service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CheckOutCommand implements Command {
    private final Scanner sc = new Scanner(System.in);
    @Autowired
    private CheckOutService checkOutService;

    @Override
    public void execute() {
        System.out.println("Enter your reservation code please");
        String reservationCodeCheckIn = sc.next();
        System.out.println("Enter hotel id please");
        Long hotelId = sc.nextLong();
        checkOutService.checkOut(hotelId,reservationCodeCheckIn);
    }
}
