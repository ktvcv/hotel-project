package dss.hotelproject.cli.cli;

import dss.hotelproject.cli.Exceptions.NoDataFoundException;

import java.nio.charset.MalformedInputException;

public interface Command {
    void execute() throws MalformedInputException, NoDataFoundException, NoDataFoundException;
}
