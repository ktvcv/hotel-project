package uca.dss20192020.hotelproject.Service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import uca.dss20192020.hotelproject.Model.Room;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {
    @Mock
    ReservationService reservationService;

    @Before
    public void beforeClass() throws Exception {
        System.out.println("@BeforeClass method will be executed before JUnit test for"
                + "a Class starts");

        MockitoAnnotations.initMocks(this);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("@BeforeClass method will be executed before JUnit test for"
                + "a Class starts");
    }

    @Test
    @Ignore
    public void findFreeTypeRoomsForPeriod() {
        when(reservationService.findFreeTypeRoomsForPeriod(1L, LocalDate.now().minusDays(3),LocalDate.now(),1)).thenReturn(Collections.emptyList());
        assertEquals(reservationService.findFreeTypeRoomsForPeriod(1L, LocalDate.now().minusDays(3),LocalDate.now(),1),Collections.emptyList());
    }


    @Test
    public void getFreeRoomsForCheckIn() {
        Room room = new Room();
        when(reservationService.getFreeRoomsForCheckIn(1L, "code")).thenReturn(Collections.singletonList(room));
        assertEquals(reservationService.getFreeRoomsForCheckIn(1L, "code").size(), 1);
    }

    @Test
    public void getReservationsWithinDates() {

    }

    @Test
    @Ignore
    public void checkIn() {
        when(reservationService.checkIn(1L,1L, "existingcode")).thenReturn(true);
        when(reservationService.checkIn(1L,1L, "fakecode")).thenReturn(false);
        assertTrue(reservationService.checkIn(1L,1L, "existingcode"));
        assertFalse(reservationService.checkIn(1L,1L, "fakecode"));
        verify(reservationService, times(1)).checkIn(1L,1L, "existingcode");

     }

    @Test
    @Ignore
    public void checkOut() {
        when(reservationService.checkOut(1L, "code")).thenReturn(true);
    }

}