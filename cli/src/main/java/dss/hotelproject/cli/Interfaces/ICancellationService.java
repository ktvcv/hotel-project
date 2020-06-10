package dss.hotelproject.cli.Interfaces;

public interface ICancellationService {
    double cancelReservation(Long hotelId, String reservationCode);
}
