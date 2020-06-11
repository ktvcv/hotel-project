package uca.dss20192020.hotelproject.Model;

import java.io.Serializable;
import java.util.List;

public class Hotel implements Serializable {
    private Long id;
    private String city;
    private String Address;
    private String email;
    private String name;
    private int stars;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Guest>  guests;

    public Hotel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }


    public static final class HotelBuilder {
        private Long id;
        private String city;
        private String Address;
        private String email;
        private String name;
        private int stars;
        private List<Room> rooms;
        private List<Reservation> reservations;
        private List<Guest> guestList;

        private HotelBuilder() {
        }

        public HotelBuilder(Long id)
        {
            this.id = id;
        }

        public static HotelBuilder aHotel() {
            return new HotelBuilder();
        }

        public HotelBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public HotelBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public HotelBuilder withAddress(String Address) {
            this.Address = Address;
            return this;
        }

        public HotelBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public HotelBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public HotelBuilder withStars(int stars) {
            this.stars = stars;
            return this;
        }

        public HotelBuilder withRooms(List<Room> rooms) {
            this.rooms = rooms;
            return this;
        }

        public HotelBuilder withReservations(List<Reservation> reservations) {
            this.reservations = reservations;
            return this;
        }

        public HotelBuilder withGuests(List<Guest> guests) {
            this.guestList = guests;
            return this;
        }

        public Hotel build() {
            Hotel hotel = new Hotel();
            hotel.setId(id);
            hotel.setCity(city);
            hotel.setAddress(Address);
            hotel.setEmail(email);
            hotel.setName(name);
            hotel.setStars(stars);
            hotel.setRooms(rooms);
            hotel.setReservations(reservations);
            hotel.setGuests(guestList);
            return hotel;
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", Address='" + Address + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", rooms=" + rooms +
                ", reservations=" + reservations +
                ", guests=" + guests +
                '}';
    }
}
