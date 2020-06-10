package dss.hotelproject.cli.Interfaces;


import dss.hotelproject.cli.Enum.Services;
import dss.hotelproject.cli.Model.Reservation;
import dss.hotelproject.cli.Payment.PaymentStrategy;

public interface IReservationManage {
    void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy);
    void addServiceToReservation(Reservation reservation, Services services);
}
