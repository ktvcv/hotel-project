package uca.dss20192020.hotelproject.Model;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class CreditCard implements Serializable {
    private Long id;
    @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^[0-9]{16}$")
    private String cardNumber;
    @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^[0-9]{3}$")
    private String cardCVC;
    @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^(1[0-2]|[1-9])$")
    private String expMonth;
    @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^[2][0-9]$")
    private String expYear;

    public CreditCard() {
    }

    public CreditCard(@Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^[0-9]{16}$") String cardNumber,
                      @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^[0-9]{3}$") String cardCVC,
                      @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^(1[0-2]|[1-9])$") String expMonth,
                      @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "^[2][0-9]$") String expYear) {

        this.cardNumber = cardNumber;
        this.cardCVC = cardCVC;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardCVC() {
        return cardCVC;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }


    public static final class CreditCardBuilder {
        private Long id;
        @Pattern(message = "Data is not correct: ${validatedValue}",
                regexp = "^[0-9]{16}$")
        private String cardNumber;
        @Pattern(message = "Data is not correct: ${validatedValue}",
                regexp = "^[0-9]{3}$")
        private String cardCVC;
        @Pattern(message = "Data is not correct: ${validatedValue}",
                regexp = "^(1[0-2]|[1-9])$")
        private String expMonth;
        @Pattern(message = "Data is not correct: ${validatedValue}",
                regexp = "^[2][0-9]$")
        private String expYear;

        private CreditCardBuilder() {
        }

        public static CreditCardBuilder aCreditCard() {
            return new CreditCardBuilder();
        }

        public CreditCardBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CreditCardBuilder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CreditCardBuilder withCardCVC(String cardCVC) {
            this.cardCVC = cardCVC;
            return this;
        }

        public CreditCardBuilder withExpMonth(String expMonth) {
            this.expMonth = expMonth;
            return this;
        }

        public CreditCardBuilder withExpYear(String expYear) {
            this.expYear = expYear;
            return this;
        }

        public CreditCard build() {
            CreditCard creditCard = new CreditCard();
            creditCard.cardCVC = this.cardCVC;
            creditCard.id = this.id;
            creditCard.expMonth = this.expMonth;
            creditCard.cardNumber = this.cardNumber;
            creditCard.expYear = this.expYear;
            return creditCard;
        }
    }
}
