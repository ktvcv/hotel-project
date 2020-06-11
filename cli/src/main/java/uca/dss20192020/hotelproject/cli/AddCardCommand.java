package uca.dss20192020.hotelproject.cli;

import uca.dss20192020.hotelproject.Model.CreditCard;
import uca.dss20192020.hotelproject.Model.Reservation;
import uca.dss20192020.hotelproject.Payment.CreditCardPayment;
import uca.dss20192020.hotelproject.RepoImpl.ReservationRepoImpl;

import java.nio.charset.MalformedInputException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AddCardCommand implements Command{
    private final Reservation.ReservationBuilder reservationBuilder;
    private final ReservationRepoImpl reservationRepo = new ReservationRepoImpl();
    private final ChoiceCommand choiceCommand = new ChoiceCommand();
    private final Long hotelId;

    public AddCardCommand(Reservation.ReservationBuilder reservationBuilder, Long hotelId) {
        this.reservationBuilder = reservationBuilder;
        this.hotelId = hotelId;
    }

    @Override
    public void execute() throws MalformedInputException {
        Scanner sc = new Scanner(System.in);
        String cardNumber;
        String cvc;
        String month;
        String year;
        System.out.println("We need you to leave your card requisites for confirm reservation");
        System.out.println("Enter card number (16 digits)");
        cardNumber = sc.next();
        System.out.println("Enter cvc (3 digits)");
        cvc = sc.next();
        System.out.println("Enter month of expiry (1 - 12)");
        month = sc.next();
        System.out.println("Enter year of expiry (20 - 29)");
        year = sc.next();


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
                        .build())
                        .withIsApproved(true);

        Reservation reservation = reservationBuilder.build();
        reservationRepo.saveNewReservation(hotelId,reservation);
        System.out.println(reservationRepo.getAllReservation(hotelId));
        System.out.println("Your reservation code : " + reservation.getConfirmationCode());
        choiceCommand.execute();


    }
}
