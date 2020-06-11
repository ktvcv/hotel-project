package uca.dss20192020.hotelproject.util;

import uca.dss20192020.hotelproject.Model.Reservation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GeneratorHelper {

    public static void generateBill(Reservation reservation) {

        String fileName = reservation.getConfirmationCode() + "receipt.txt";

        String fileContent = "The reservation code :" +
                reservation.getConfirmationCode() +
                "\n First day: " +
                reservation.getDateFrom() +
                "\n Last day: " +
                reservation.getDateTo() +
                "\n Total cost: " +
                reservation.getTotalCost();

        System.out.println(fileContent);

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeUTF(fileContent);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    public static String generateReservationCode() {
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int count = 13;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();

    }
}
