package dss.hotelproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"hotel_id", "roomNumber"})
)
public class Room{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private RoomType type;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private RoomTypeByMaxNumOfPerson typeOfPerson;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private RoomStatus roomStatus;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @NotNull
    private Hotel hotel;

    @NotNull
    private String roomNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public RoomTypeByMaxNumOfPerson getTypeOfPerson() {
        return typeOfPerson;
    }

    public void setTypeOfPerson(RoomTypeByMaxNumOfPerson typeOfPerson) {
        this.typeOfPerson = typeOfPerson;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                ", type=" + type +
                ", typeOfPerson=" + typeOfPerson +
                ", roomStatus=" + roomStatus +
                ", guest=" + guest +
                ", hotel=" + hotel.getName() +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }

    public static final class RoomBuilder {
        private Long id;
        private RoomType type;
        private RoomTypeByMaxNumOfPerson typeOfPerson;
        private RoomStatus roomStatus;
        private Guest guest;
        private Hotel hotel;
        private String roomNumber;

        public RoomBuilder() {
        }

        public static RoomBuilder aRoom() {
            return new RoomBuilder();
        }

        public RoomBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RoomBuilder withType(RoomType type) {
            this.type = type;
            return this;
        }

        public RoomBuilder withTypeOfPerson(RoomTypeByMaxNumOfPerson typeOfPerson) {
            this.typeOfPerson = typeOfPerson;
            return this;
        }

        public RoomBuilder withRoomStatus(RoomStatus roomStatus) {
            this.roomStatus = roomStatus;
            return this;
        }

        public RoomBuilder withGuest(Guest guest) {
            this.guest = guest;
            return this;
        }

        public RoomBuilder withHotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public RoomBuilder withRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Room build() {
            Room room = new Room();
            room.setId(id);
            room.setType(type);
            room.setTypeOfPerson(typeOfPerson);
            room.setRoomStatus(roomStatus);
            room.setGuest(guest);
            room.setHotel(hotel);
            room.setRoomNumber(roomNumber);
            return room;
        }
    }
}
