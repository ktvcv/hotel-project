package uca.dss20192020.hotelproject.Exceptions;

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
