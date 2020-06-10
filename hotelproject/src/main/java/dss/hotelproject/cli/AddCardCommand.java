package dss.hotelproject.cli;

import dss.hotelproject.Exceptions.NoDataFoundException;
import dss.hotelproject.Model.CreditCard;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Payment.CreditCardPayment;
import dss.hotelproject.repos.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.MalformedInputException;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class AddCardCommand implements Command{
    Reservation.ReservationBuilder reservationBuilder;

    public void setReservationBuilder(Reservation.ReservationBuilder reservationBuilder) {
        this.reservationBuilder = reservationBuilder;
    }

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    QuitCommand quitCommand;

    @Override
    public void execute() throws MalformedInputException, NoDataFoundException {
        Scanner sc = new Scanner(System.in);
        String cardNumber;
        String cvc;
        String month;
        String year;
        Pattern patternNumber = Pattern.compile("^[0-9]{16}$");
        Pattern patternCVC = Pattern.compile("^[0-9]{3}$");
        Pattern patternMonth = Pattern.compile("^(1[0-2]|[1-9])$");
        Pattern patternYear = Pattern.compile("^[2][0-9]$");


        do {
            quitCommand.execute();
            System.out.println("We need you to leave your card requisites for confirm reservation");
            System.out.println("Enter card number (16 digits)");
            cardNumber = sc.next();
            System.out.println("Enter cvc (3 digits)");
            cvc = sc.next();
            System.out.println("Enter month of expiry (1 - 12)");
            month = sc.next();
            System.out.println("Enter year of expiry (20 - 29)");
            year = sc.next();
        }
        while (patternNumber.matcher(cardNumber).matches() &&
               patternCVC.matcher(cvc).matches() &&
               patternMonth.matcher(month).matches() &&
               patternYear.matcher(year).matches());

        reservationBuilder.withPaymentMethod(
                CreditCardPayment
                        .CreditCardPaymentBuilder
                        .aCreditCardPayment()
                        .withCreditCard(
                                CreditCard.CreditCardBuilder
                                        .aCreditCard()
                                        .withCardNumber(cardNumber)
                                        .withCardCVC(cvc)
                                        .withExpMonth(month)
                                        .withExpYear(year)
                                        .build())
                        .build());

        Reservation reservation = reservationBuilder.build();
        reservationRepo.save(reservation);

    }
}
