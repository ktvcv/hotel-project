package uca.dss20192020.hotelproject.Service;

import org.springframework.stereotype.Service;
import uca.dss20192020.hotelproject.Interfaces.IPay;
import uca.dss20192020.hotelproject.Payment.PaymentStrategy;

@Service
public class PaymentService implements IPay {
    @Override
    public boolean pay(PaymentStrategy paymentMethod, double cost) {
        paymentMethod.chargeMoney(cost);
        return true;
    }

}
