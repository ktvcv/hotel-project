package uca.dss20192020.hotelproject.cli;

import uca.dss20192020.hotelproject.Model.Guest;
import uca.dss20192020.hotelproject.RepoImpl.GuestRepoImpl;

import java.nio.charset.MalformedInputException;
import java.util.Scanner;

public class CreateGuestCommand implements Command {
    private final GuestRepoImpl guestRepo = new GuestRepoImpl();
    private final String DNI;
    private final Long hotelId;

    public CreateGuestCommand(String DNI, Long hotelId) {
        this.DNI = DNI;
        this.hotelId = hotelId;
    }

    @Override
    public void execute() throws MalformedInputException {
        Scanner sc = new Scanner(System.in);
        System.out.println("You must be registered to reserve room");
        System.out.println("Enter your name please");
        String name = sc.next();
        System.out.println("Enter your surname please");
        String surname = sc.next();
        System.out.println("Enter your mobile please");
        String mobile = sc.next();
        Guest newGuest = Guest.GuestBuilder
                .aGuest()
                .withDNI(DNI)
                .withName(name)
                .withSurname(surname)
                .withMobile(mobile)
                .build();

        final FindCommand findCommand= new FindCommand(hotelId);

        guestRepo.addGuest(hotelId, newGuest);
        System.out.println(guestRepo.getGuestByDni(hotelId,DNI));
        findCommand.execute();

    }
}
