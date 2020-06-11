package uca.dss20192020.hotelproject.Service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import uca.dss20192020.hotelproject.Model.Hotel;
import uca.dss20192020.hotelproject.RepoImpl.HotelRepoImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HotelRepoTest {

    @Mock
    HotelRepoImpl dataAccess;


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
    public void saveHotel() {
        final Hotel hotel = Hotel.HotelBuilder.aHotel()
                .withId(1L)
                .withCity("Cadiz")
                .build();
        when(dataAccess.saveHotel(any(Hotel.class))).thenReturn(hotel);
        Hotel returnedHotel = dataAccess.saveHotel(new Hotel());
        verify(dataAccess, times(1)).saveHotel(any(Hotel.class));
        assertEquals(hotel.getName(), returnedHotel.getName());

    }

    @Test
    public void getHotel() {
        final Hotel hotel = Hotel.HotelBuilder.aHotel()
                .withId(1L)
                .withCity("Cadiz")
                .build();
        when(dataAccess.getHotel(1L)).thenReturn(hotel);
        assertEquals(dataAccess.getHotel(1L), hotel);
        verify(dataAccess, times(1)).getHotel(1L);
    }

//    @Test
//    public void getReservationByReservationCode() {
//        final Reservation reservation = Reservation.ReservationBuilder.aReservation()
//                .withConfirmationCode("code")
//                .build();
//        when(dataAccess.getReservationByReservationCode(1L,"code")).thenReturn(reservation);
//        assertEquals(dataAccess.getReservationByReservationCode(1L, "code"), reservation);
//        verify(dataAccess, times(1)).saveHotel(any(Hotel.class));
//    }
//
//    @Test
//    public void getAllRooms() {
//        List<Room> rooms = new ArrayList<>();
//        Room room1 = new Room();
//        rooms.add(room1);
//        Room room2 = new Room();
//        rooms.add(room2);
//        when(dataAccess.getAllRooms(1L)).thenReturn(rooms);
//        assertEquals(dataAccess.getAllRooms(1L),rooms);
//        assertEquals(dataAccess.getAllRooms(1L).size(),2);
//        verify(dataAccess, times(2)).getAllRooms(1L);
//    }
//
//    @Test
//    public void removeReservation() {
//        when(dataAccess.removeReservation(1L, "code")).thenReturn(true);
//        assertTrue(dataAccess.removeReservation(1L, "code"));
//        assertFalse(dataAccess.removeReservation(1L, "fakeCode"));
//        verify(dataAccess, times(2)).removeReservation(1L,anyString());
//    }
//
//    @Test
//    @Ignore
//    public void getAllRoomInHotelForNumberOfGuests() {
////       Triple triple = new Triple(RoomType.STANDARD, RoomTypeByMaxNumOfPerson.DOUBLE,3,20.0);
////       when(dataAccess.getAllRoomInHotelForNumberOfGuests(1L,1)).thenReturn(Collections.singletonList(triple));
////       assertEquals(dataAccess.getAllRoomInHotelForNumberOfGuests(1L,1),Collections.singletonList(triple));
////       assertEquals(dataAccess.getAllRoomInHotelForNumberOfGuests(1L,2), Collections.EMPTY_LIST);
//
//    }
//
//    @Test
//    @Ignore
//    public void getRoomById() {
//        final Room room = Room.RoomBuilder.aRoom()
//                .withId(2L)
//                .build();
//        when(dataAccess.getRoomById(1L,2L)).thenReturn(room);
//        assertNotNull(dataAccess.getRoomById(1L, 2L));
//        assertEquals(dataAccess.getRoomById(1L, 2L), room);
//        verify(dataAccess, times(2)).getRoomById(1L, 2L);
//
//    }
//
//    @Test
//    @Ignore
//    public void getReservationById() {
//        Reservation reservation = new Reservation();
//        reservation.setId(1L);
//        when(dataAccess.getReservationById(1L, 1L)).thenReturn(reservation);
//        assertEquals(reservation,dataAccess.getReservationById(1L, 1L));
//    }
//
//    @Test
//    @Ignore
//    public void saveNewReservation() {
//        Reservation reservation = new Reservation();
//        reservation.setConfirmationCode("code");
//        when(dataAccess.saveNewReservation(1L,reservation )).thenReturn(reservation);
//        assertNotNull(dataAccess.saveNewReservation(1L,reservation));
//        verify(dataAccess, times(1)).saveNewReservation(anyLong(), any(Reservation.class));
//        verify(dataAccess, times(1)).saveHotel(any(Hotel.class));
//    }
//
//    @Test
//    @Ignore
//    public void addNewRoom() {
//        Room room = new Room();
//        room.setId(1L);
//        when(dataAccess.saveNewRoom(1L, room).thenReturn(reservation);
//        assertNotNull(dataAccess.saveNewReservation(1L,reservation));
//        verify(dataAccess, times(1)).saveNewReservation(anyLong(), any(Reservation.class));
//        verify(dataAccess, times(1)).saveHotel(any(Hotel.class));
//    }
//
//    @Test
//    @Ignore
//    public void deleteRoom() {
//
//
//    }
//
//    @Test
//    @Ignore
//    public void setPriceForRoomType() {
//
//    }
//    @Test
//    @Ignore
//    public void addGuest() {
////        dataAccess.addGuest(1L,new Guest.GuestBuilder("test").build());
////        assertNotNull(dataAccess.getGuestByDni(1L, "test"));
//    }
//
//    @Test
//    @Ignore
//    public void getGuestByDni() {
//        assertEquals("dni1", dataAccess.getGuestByDni(1L, "dni1").getDNI());
//    }
//
//    @Test
//    @Ignore
//    public void getAllServicesList() {
//    }
}