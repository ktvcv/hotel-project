package dss.hotelproject.cli.Interfaces;


import dss.hotelproject.cli.Payment.PaymentStrategy;

public interface IPay {
    boolean pay(PaymentStrategy paymentMethod, double cost);
}
