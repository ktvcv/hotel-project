package dss.hotelproject.cli;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dss.hotelproject.Exceptions.NoDataFoundException;

import java.nio.charset.MalformedInputException;

public interface Command {
    void execute() throws MalformedInputException, MismatchedInputException, NoDataFoundException;
}
