package dss.hotelproject.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No found data")
public class NoDataFoundException extends Exception {
    String str1;
    /* Constructor of custom exception class
     * here I am copying the message that we are passing while
     * throwing the exception to a string and then displaying
     * that string along with the message.
     */
    public NoDataFoundException(String str2) {
        str1=str2;
    }
    public String toString(){
        return ("No data fount exception occurred: "+str1) ;
    }
}
