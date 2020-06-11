package uca.dss20192020.hotelproject.RepoImpl;

import uca.dss20192020.hotelproject.Interfaces.IHotelRepo;
import uca.dss20192020.hotelproject.Model.Hotel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class HotelRepoImpl implements IHotelRepo {

    public Hotel getHotel(Long id) {

        List<Hotel> hotelList = new ArrayList<>();
        Optional<Hotel> hotelOptional = Optional.empty();
        try
        {
            FileInputStream fis = new FileInputStream("hotels.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            hotelList = (ArrayList) ois.readObject();

            hotelOptional = hotelList
                    .stream()
                    .filter(x -> x.getId().equals(id))
                    .findAny();


        } catch (IOException | ClassNotFoundException | NoSuchElementException e) {
            e.printStackTrace();

        }
        return hotelOptional.orElse(null);
    }

    public Hotel saveHotel(Hotel hotel) {

        List<Hotel> hotelList = new ArrayList<>();
        try {
            File file = new File("hotels.dat");
            FileInputStream fis = new FileInputStream("hotels.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            hotelList = (ArrayList<Hotel>) ois.readObject();

            hotelList.removeIf(x -> (
                    x.getId().equals(hotel.getId())
            ));

            hotelList.add(hotel);
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream("hotels.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hotelList);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return hotel;
    }





}