package dss.hotelproject.cli.Service;

import dss.hotelproject.cli.Interfaces.IPay;
import dss.hotelproject.cli.Payment.PaymentStrategy;
import org.springframework.stereotype.Service;


@Service
public class PaymentService implements IPay {
    @Override
    public boolean pay(PaymentStrategy paymentMethod, double cost) {
        paymentMethod.chargeMoney(cost);
        return true;
    }

}
