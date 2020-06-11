package uca.dss20192020.hotelproject.cli;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.nio.charset.MalformedInputException;

public interface Command {
    void execute() throws MalformedInputException, MismatchedInputException;
}
