package dss.hotelproject.Interfaces;

public interface ICancellationService {
    double cancelReservation(Long hotelId, String reservationCode);
}
