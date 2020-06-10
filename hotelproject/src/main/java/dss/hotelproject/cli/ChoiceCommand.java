package dss.hotelproject.cli;

import dss.hotelproject.Exceptions.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.MalformedInputException;
import java.util.Scanner;

@Component
public class ChoiceCommand implements Command {

    @Autowired
    private FindCommand findCommand;

    @Autowired
    private CheckOutCommand checkOutCommand;

    @Autowired
    private CheckInCommand checkInCommand;

    @Autowired
    private CancelCommand cancelCommand;


    private final Scanner sc = new Scanner(System.in);

    @Override
    public void execute() throws MalformedInputException, NoDataFoundException {

        while (true) {
            System.out.println("Choose option please:");
            System.out.println("1. Find room for your dates");
            System.out.println("2. Cancel your reservation");
            System.out.println("3. Check-in");
            System.out.println("4. Check-out");
            System.out.println("5. Init Test Data");
            System.out.println("6. Exit");
            int number = sc.nextInt();


            switch (number) {
                case 1:
                    findCommand.execute();
                    break;
                case 2:
                    cancelCommand.execute();
                    break;
                case 3:
                    checkInCommand.execute();
                    break;
                case 4:
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


