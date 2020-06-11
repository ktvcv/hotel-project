package uca.dss20192020.hotelproject.Service;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uca.dss20192020.hotelproject.Payment.CreditCardPayment;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    PaymentService paymentService;
    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("@BeforeClass method will be executed before JUnit test for"
                + "a Class starts");
    }

    @Test
    @Ignore
    public void pay() {
        when(paymentService.pay(new CreditCardPayment(),100)).thenReturn(true);
        assertTrue(paymentService.pay(new CreditCardPayment(),100));
    }
}
