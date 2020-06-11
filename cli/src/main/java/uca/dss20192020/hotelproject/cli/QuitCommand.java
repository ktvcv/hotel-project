package uca.dss20192020.hotelproject.cli;

import java.nio.charset.MalformedInputException;
import java.util.Scanner;

public class QuitCommand implements Command {
    private final Scanner sc = new Scanner(System.in);
    private final ChoiceCommand choiceCommand = new ChoiceCommand();
    @Override
    public void execute() throws MalformedInputException {
        System.out.println("Continue? Press no for quit");
        String quit = sc.next();
        if (quit.toLowerCase().equals("no"))
            choiceCommand.execute();
    }
}
