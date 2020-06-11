package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Enum.Services;
import uca.dss20192020.hotelproject.Payment.PaymentStrategy;

public interface IReservationManage {
    void addServiceToReservation(Long hotelId, String reservationCode, Services services);
    void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy);
}
