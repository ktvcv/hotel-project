package uca.dss20192020.hotelproject.cli;

import uca.dss20192020.hotelproject.Service.ReservationService;

import java.nio.charset.MalformedInputException;
import java.util.Scanner;

public class ChoiceCommand implements Command {

    private final ReservationService reservationService = new ReservationService();

    Scanner sc = new Scanner(System.in);

    public ChoiceCommand() {
    }

    @Override
    public void execute() throws MalformedInputException {
        System.out.println("Welcome, guest");
        Long hotelId;
        do {
            System.out.println("Enter hotel id please:");
            hotelId = sc.nextLong();
        } while (!reservationService.ifHotelExists(hotelId));

        while (true) {
            System.out.println("Choose option please:");
            System.out.println("1. Find room for your dates");
            System.out.println("2. Cancel your reservation");
            System.out.println("3. Check-in");
            System.out.println("4. Check-out");
            System.out.println("5. Exit");
            int number = sc.nextInt();


            switch (number) {
                case 1:
                    FindCommand findCommand = new FindCommand(hotelId);
                    findCommand.execute();
                    break;
                case 2:
                    CancelCommand cancelCommand = new CancelCommand(hotelId);
                    cancelCommand.execute();
                    break;
                case 3:
                    CheckInCommand checkInCommand = new CheckInCommand(hotelId);
                    checkInCommand.execute();
                    break;
                case 4:
                    CheckOutCommand checkOutCommand = new CheckOutCommand(hotelId);
                    checkOutCommand.execute();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("No such number");
                    break;


            }


        }

    }
}


