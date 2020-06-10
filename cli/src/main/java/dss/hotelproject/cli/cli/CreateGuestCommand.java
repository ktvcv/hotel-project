package dss.hotelproject.cli.cli;


import dss.hotelproject.cli.Model.Guest;
import dss.hotelproject.cli.repos.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateGuestCommand implements Command {
    @Autowired
    private GuestRepo guestRepo;
    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You must be registered to reserve room");
        System.out.println("Enter your name please");
        String name = sc.next();
        System.out.println("Enter your surname please");
        String surname = sc.next();
        System.out.println("Enter your mobile please");
        String mobile = sc.next();
        System.out.println("Enter your dni");
        String dni = sc.next();
        Guest newGuest = Guest.GuestBuilder
                .aGuest()
                .withDNI(dni)
                .withName(name)
                .withSurname(surname)
                .withMobile(mobile)
                .build();

        guestRepo.save(newGuest);
    }
}
