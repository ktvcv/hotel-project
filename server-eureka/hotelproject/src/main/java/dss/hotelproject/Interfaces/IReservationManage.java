package dss.hotelproject.Interfaces;


import dss.hotelproject.Payment.PaymentStrategy;

public interface IReservationManage {
    void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy);
}
