package uca.dss20192020.hotelproject.RepoImpl;


import uca.dss20192020.hotelproject.Enum.Services;
import uca.dss20192020.hotelproject.Interfaces.IReservationRepo;
import uca.dss20192020.hotelproject.Model.Hotel;
import uca.dss20192020.hotelproject.Model.Reservation;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReservationRepoImpl implements IReservationRepo {
    HotelRepoImpl hotelRepo = new HotelRepoImpl();

    @Override
    public Reservation getReservationByReservationCode(Long hotelId, String reservationCode) {
        List<Reservation> reservations = getAllReservation(hotelId);

        Optional<Reservation> reservation = reservations
                .stream()
                .filter(x -> x.getConfirmationCode().equals(reservationCode))
                .findAny();
        return reservation.orElse(null);
    }


    @Override
    public boolean removeReservation(Long hotelId, String reservationCode) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Reservation> reservations = hotel.getReservations();
        if(reservations.removeIf(x -> x.getConfirmationCode().equals(reservationCode)))
        {
            hotel.setReservations(reservations);
            return true;
        }
        else
            return false;

    }

    @Override
    public Reservation getReservationById(Long hotelId, Long reservationId) {
        List<Reservation> reservations = getAllReservation(hotelId);

        Optional<Reservation> room = reservations
                .stream()
                .filter(x -> x.getId().equals(reservationId))
                .findAny();

        return room.orElse(null);

    }

    @Override
    public void saveChangedReservation(Long hotelId, Reservation reservation) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Reservation> reservationList = hotel.getReservations();
        reservationList.removeIf(x -> x.getConfirmationCode().equals(reservation.getConfirmationCode()));
        reservationList.add(reservation);

        hotel.setReservations(reservationList);
        hotelRepo.saveHotel(hotel);
    }

    @Override
    public Reservation saveNewReservation(Long hotelId, Reservation reservation) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        reservation.setTotalCost(getTotalPrice(reservation));
        List<Reservation> reservationList = hotel.getReservations();
        reservationList.add(reservation);

        hotel.setReservations(reservationList);
        System.out.println(hotel.getReservations());
        hotelRepo.saveHotel(hotel);
        return reservation;
    }

    private double getTotalPrice(Reservation reservation)
    {
        return  reservation.getRoomType().getPriceByType()*
                reservation.getRoomTypeByMaxNumOfPerson().getNumberOfPersonsByType()*
                ChronoUnit.DAYS.between(reservation.getDateFrom(),reservation.getDateTo());
    }

    @Override
    public List<Services> getAllServicesList(Long hotelId, String reservationCode) {
        Reservation reservations = getReservationByReservationCode(hotelId, reservationCode);
        if(reservations!=null) {
            List<Services> servicesList = reservations.getServicesList();
            if (servicesList==null)
                return Collections.emptyList();
            return servicesList;
        }
        else
            return Collections.emptyList();
    }

    @Override
    public List<Reservation> getAllReservation(Long hotelId) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        return hotel.getReservations();
    }
}
