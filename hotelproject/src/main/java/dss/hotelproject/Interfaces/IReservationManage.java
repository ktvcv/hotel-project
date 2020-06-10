package dss.hotelproject.Interfaces;


import dss.hotelproject.Enum.Services;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Payment.PaymentStrategy;

public interface IReservationManage {
    void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy);
    void addServiceToReservation(Reservation reservation, Services services);
}
