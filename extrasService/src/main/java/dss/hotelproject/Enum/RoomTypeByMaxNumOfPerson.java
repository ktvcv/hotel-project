package dss.hotelproject.Enum;

import java.io.Serializable;

public enum RoomTypeByMaxNumOfPerson implements Serializable {
    SINGLE (1),
    DOUBLE (2),
    FAMILY (3);

    private final int persons;


    // Creates a RoomType object
    RoomTypeByMaxNumOfPerson(int persons){
        this.persons = persons;
    }

    // Getter to access to the price of each type of room
    public int getNumberOfPersonsByType(){ return persons; }
}
