package dss.hotelproject.Payment;


import dss.hotelproject.Model.CreditCard;

import java.io.Serializable;

public class CreditCardPayment implements  Serializable, PaymentStrategy {

    private CreditCard creditCard;

    public CreditCardPayment() {
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public CreditCardPayment(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void chargeMoney(double amount) {
        System.out.println(amount + "euros were debited from your card");
    }


    public static final class CreditCardPaymentBuilder {
        private CreditCard creditCard;

        private CreditCardPaymentBuilder() {
        }

        public static CreditCardPaymentBuilder aCreditCardPayment() {
            return new CreditCardPaymentBuilder();
        }

        public CreditCardPaymentBuilder withCreditCard(CreditCard creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public CreditCardPayment build() {
            return new CreditCardPayment(creditCard);
        }
    }
}