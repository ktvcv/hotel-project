package uca.dss20192020.hotelproject.Service;

import uca.dss20192020.hotelproject.Enum.RoomStatus;
import uca.dss20192020.hotelproject.Enum.RoomType;
import uca.dss20192020.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import uca.dss20192020.hotelproject.Enum.Services;
import uca.dss20192020.hotelproject.Exceptions.NoDataFoundException;
import uca.dss20192020.hotelproject.Interfaces.*;
import uca.dss20192020.hotelproject.Model.Hotel;
import uca.dss20192020.hotelproject.Model.Reservation;
import uca.dss20192020.hotelproject.Model.Room;
import uca.dss20192020.hotelproject.Payment.PaymentStrategy;
import uca.dss20192020.hotelproject.RepoImpl.HotelRepoImpl;
import uca.dss20192020.hotelproject.RepoImpl.ReservationRepoImpl;
import uca.dss20192020.hotelproject.RepoImpl.RoomRepoImpl;
import uca.dss20192020.hotelproject.util.Triple;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReservationService implements IReservationService, ISearchForFreeRooms,
        ICancellationService, ICheckInService, IReservationManage {

    HotelRepoImpl hotelRepo  = new HotelRepoImpl();
    PaymentService paymentService = new PaymentService();
    ReservationRepoImpl reservationRepo = new ReservationRepoImpl();
    RoomRepoImpl roomRepo = new RoomRepoImpl();

    /**
     * Method finds type of room, category of room
     * and number of free room in each type and category
     * @param hotelId : id of hotel in which searching room
     * @param firstDay : first day for searching
     * @param lastDay :
     * @param numberOfPersons: depends on which type of rooms will be found
     * @return : return list of 4 data types
     */
    @Override
    public List<Triple> findFreeTypeRoomsForPeriod(Long hotelId, LocalDate firstDay, LocalDate lastDay, int numberOfPersons) {
        List<Reservation> reservations = getReservationsWithinDates(hotelId, firstDay, lastDay);
        List<Triple> availableRoom = getAllRoomInHotelForNumberOfGuests(hotelId, numberOfPersons);

        if(reservations.isEmpty())
        {
            return availableRoom;
        }
        else{
            reservations.forEach(x -> {
                availableRoom.forEach(
                        y -> {
                            if((y.getRoomType().equals(x.getRoomType()))
                                    &&(y.getRoomTypeByMaxNumOfPerson().equals(x.getRoomTypeByMaxNumOfPerson())))
                                       y.setNumber(y.getNumber() - 1);
                        }
                );
            });

            availableRoom.removeIf(x -> x.getNumber() <= 0);

        }
        return availableRoom;
    }

    /**
     * Method that cancel reservation before guest checked-in
     * @param hotelId : hotel id in which cancellation proceed
     * @param reservation : unique string-identifier od reservation
     * @return : if reservation was canceled successfully, return charge
     */
    @Override
    public double cancelReservation(Long hotelId, Reservation reservation) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Reservation> reservations = hotel.getReservations();
        if(reservation == null || reservation.getPaymentMethod() == null) {
            return -1;
        }
        else {
            paymentService.pay(reservation.getPaymentMethod(), calculateCancellationCharge(reservation));
            reservations.removeIf(x -> (x.getConfirmationCode().equals(reservation.getConfirmationCode())));
            hotel.setReservations(reservations);
            hotelRepo.saveHotel(hotel);
            return calculateCancellationCharge(reservation);
        }
    }

    /**
     * @param reservation
     * @return
     */
    //Return the charge
    private double calculateCancellationCharge(Reservation reservation){
        LocalDate firstDay = reservation.getDateFrom();
        LocalDate dayOfCancellation = LocalDate.now();
        long dayBetween = ChronoUnit.DAYS.between(dayOfCancellation, firstDay);

        int percent;

        //system of calculating
        if (dayBetween > 30)
            percent = 0;
        else if (dayBetween >= 20)
            percent = 10;
        else if (dayBetween >= 10)
            percent = 30;
        else if (dayBetween >= 2)
            percent = 60;
        else
            percent = 100;

        return reservation.getTotalCost() * percent / 100;
    }

    @Override
    public List<Room> getFreeRoomsForCheckIn(Long hotelId, String reservationCode) {
        Reservation reservation = reservationRepo.getReservationByReservationCode(hotelId, reservationCode);
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Room> roomsInHotel = hotel.getRooms();
        List<Room> freeRooms = new ArrayList<>();
        try{
            roomsInHotel.forEach(x -> {
                if ((x.getRoomStatus() == RoomStatus.FREE)
                        && (x.getType() == reservation.getRoomType())
                        && (x.getTypeOfPerson() == reservation.getRoomTypeByMaxNumOfPerson()))
                    freeRooms.add(x);
            });

            return freeRooms;
        }catch (NullPointerException e)
        {
            System.out.println("No such reservation");
        }
        return Collections.emptyList();

    }

    public List<Reservation> getReservationsWithinDates(Long hotelId, LocalDate firstDay, LocalDate lastDay) {
        List<Reservation> allReservations = reservationRepo.getAllReservation(hotelId);
        System.out.println(allReservations);
        List<Reservation> reservationsWithinDates = new ArrayList<>();

        allReservations.forEach(x -> {
            List<LocalDate> dates = Stream.iterate(x.getDateFrom(), date -> date.plusDays(1))
                    .limit(ChronoUnit.DAYS.between(x.getDateFrom(), x.getDateTo().minusDays(1)))
                    .collect(Collectors.toList());
            if (dates.contains(firstDay) || dates.contains(lastDay.minusDays(1)))
                reservationsWithinDates.add(x);
        });

        return reservationsWithinDates;
    }

    @Override
    public boolean checkIn(Long roomId, Long hotelId, String reservationCode) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Reservation> reservations = reservationRepo.getAllReservation(hotelId);
        List<Room> rooms = roomRepo.getAllRooms(hotelId);

        Room room = roomRepo.getRoomById(roomId, hotelId);
        Reservation reservation = reservationRepo.getReservationByReservationCode(hotelId, reservationCode);
        reservations.forEach(
                x -> {
                    if (x.getConfirmationCode().equals(reservationCode)) {
                        x.setRoom(room);
                        System.out.println(room);
                        System.out.println(x);
                    }
                }
        );

        rooms.forEach(
                x -> {
                    if (x.getId().equals(roomId)) {
                        x.setRoomStatus(RoomStatus.OCCUPIED);
                        x.setGuest(reservation.getGuest());
                                            }
                }
        );
        if(paymentService.pay(reservation.getPaymentMethod(),reservation.getTotalCost())) {
            hotel.setReservations(reservations);
            hotel.setRooms(rooms);
            hotelRepo.saveHotel(hotel);
            return true;
        }
        return false;
    }

    /**
     *
     * @param hotelId
     * @param reservationCode: code fo reservation to delete, set Free status to room, which was occupied
     */
    @Override
    public boolean checkOut(Long hotelId, String reservationCode) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Room> rooms =  roomRepo.getAllRooms(hotelId);
        Reservation reservation = reservationRepo.getReservationByReservationCode(hotelId, reservationCode);
        if(reservation!=null) {
            if (calculateAdditionalPayment(reservation) > 0)
                paymentService.pay(reservation.getPaymentMethod(), calculateAdditionalPayment(reservation));

            rooms.forEach(
                    x -> {
                        if (x.getId().equals(reservation.getRoom().getId())) {
                            x.setRoomStatus(RoomStatus.FREE);
                            x.setGuest(null);
                        }
                    }
            );
            hotel.setRooms(rooms);
            reservationRepo.removeReservation(hotelId, reservationCode);
            hotelRepo.saveHotel(hotel);
            return true;
        }
        else {
            try {
                throw new NoDataFoundException("No such reservation");
            } catch (NoDataFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private double calculateAdditionalPayment(Reservation reservation)
    {
        List<Services> servicesList = reservation.getServicesList();
        double additionalCost = 0;
        if(servicesList.isEmpty())
            return additionalCost;

        for (Services service: servicesList)
            additionalCost +=service.getServicePrice();

        return additionalCost;
    }

    @Override
    public void addServiceToReservation(Long hotelId, String reservationCode, Services services) {
        List<Services> servicesList = reservationRepo.getReservationByReservationCode(hotelId, reservationCode).getServicesList();
        servicesList.add(services);
        Reservation reservation = reservationRepo.getReservationByReservationCode(hotelId, reservationCode);
        reservation.setServicesList(servicesList);
        reservationRepo.saveChangedReservation(hotelId, reservation);
    }

    @Override
    public void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy) {
        Reservation reservation = reservationRepo.getReservationByReservationCode(hotelId, reservationCode);
        reservation.setPaymentMethod(paymentStrategy);
        reservation.setApproved(true);
        reservationRepo.saveChangedReservation(hotelId, reservation);
    }

    @Override
    public boolean ifHotelExists(Long hotelId) {
        try {
            hotelRepo.getHotel(hotelId);
        }catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }

    @Override
    public List<Triple> getAllRoomInHotelForNumberOfGuests(Long hotelId, int numberOfPersons) {
        List<Room> rooms = roomRepo.getAllRooms(hotelId);

        List<Triple> roomsInHotel = new ArrayList<>();

        RoomType[] roomTypes = RoomType.values();
        RoomTypeByMaxNumOfPerson[] roomTypeOfPeople = RoomTypeByMaxNumOfPerson.values();

        for (RoomType rt: roomTypes)
            for(RoomTypeByMaxNumOfPerson rtP: roomTypeOfPeople)
                roomsInHotel.add(new Triple(rt,rtP,0,rt.getPriceByType()*rtP.getNumberOfPersonsByType()));

        roomsInHotel.forEach(x ->
        {
            rooms.forEach(y ->
                    {
                        if((x.getRoomType().equals(y.getType()))
                                &&(x.getRoomTypeByMaxNumOfPerson().equals(y.getTypeOfPerson()))
                                &&(y.getTypeOfPerson().getNumberOfPersonsByType()>=numberOfPersons))
                            x.setNumber(x.getNumber()+1);
                    }
            );
        });

        roomsInHotel.removeIf(x -> x.getNumber()==0);

        return roomsInHotel;
    }
}
