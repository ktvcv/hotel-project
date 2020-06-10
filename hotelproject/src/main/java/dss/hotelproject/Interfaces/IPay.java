package dss.hotelproject.Interfaces;


import dss.hotelproject.Payment.PaymentStrategy;

public interface IPay {
    boolean pay(PaymentStrategy paymentMethod, double cost);
}
