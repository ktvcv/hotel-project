package dss.hotelproject.Service;

import dss.hotelproject.Interfaces.IPay;
import dss.hotelproject.Payment.CreditCardPayment;
import dss.hotelproject.Payment.PaymentStrategy;
import org.springframework.stereotype.Service;


@Service
public class PaymentService implements IPay {
    @Override
    public boolean pay(PaymentStrategy paymentMethod, double cost) {
        new CreditCardPayment().chargeMoney(cost);
        return true;
    }

}
