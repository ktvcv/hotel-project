package com.uca.dss20192020.hotelprojectclientwebservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {

    Long id;
    String city;
    String Address;

    String email;

    String name;
    int stars;

    List<Room> rooms;
    List<Reservation> reservations;

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


    public static final class HotelBuilder {
        private Long id;
        private String city;
        private String Address;
        private String email;
        private String name;
        private int stars;
        private List<Room> rooms;
        private List<Reservation> reservations;

        public HotelBuilder() {
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
                '}';
    }
}
