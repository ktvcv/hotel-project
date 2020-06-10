package dss.hotelproject.cli.Model;

public class CreditCard{
    private String cardNumber;
    private String cardCVC;
    private String expMonth;
    private String expYear;

    public CreditCard() {
    }

    public CreditCard( String cardNumber,
                       String cardCVC,
                       String expMonth,
                       String expYear) {

        this.cardNumber = cardNumber;
        this.cardCVC = cardCVC;
        this.expMonth = expMonth;
        this.expYear = expYear;
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
        private String cardNumber;
        private String cardCVC;

        private String expMonth;
        private String expYear;

        private CreditCardBuilder() {
        }

        public static CreditCardBuilder aCreditCard() {
            return new CreditCardBuilder();
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
            creditCard.expMonth = this.expMonth;
            creditCard.cardNumber = this.cardNumber;
            creditCard.expYear = this.expYear;
            return creditCard;
        }
    }
}
