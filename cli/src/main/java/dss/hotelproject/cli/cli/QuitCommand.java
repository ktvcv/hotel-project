package dss.hotelproject.cli.cli;


import dss.hotelproject.cli.Exceptions.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.MalformedInputException;
import java.util.Scanner;

@Component
public class QuitCommand implements Command {
    private final Scanner sc = new Scanner(System.in);
    @Autowired
    private ChoiceCommand choiceCommand;
    @Override
    public void execute() throws MalformedInputException, NoDataFoundException {
        System.out.println("Continue? Press no for quit");
        String quit = sc.next();
        if (quit.toLowerCase().equals("no"))
            choiceCommand.execute();
    }
}
