package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Payment.PaymentStrategy;

public interface IPay {
    boolean pay(PaymentStrategy paymentMethod, double cost);
}
