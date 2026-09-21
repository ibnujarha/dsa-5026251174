package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("rentals.txt"));

            int totalRentals = scanner.nextInt();
            Rental[] rentals = new Rental[totalRentals];
            int[] units = new int[totalRentals];

            for (int i = 0; i < totalRentals; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                units[i] = scanner.nextInt();

                if (type.equals("LAPTOP")) {
                    rentals[i] = new LaptopRental(id, days);
                } else {
                    rentals[i] = new ProjectorRental(id, days);
                }
            }

            scanner.close();

            for (int i = 0; i < rentals.length; i++) {
                System.out.println(
                    rentals[i].getId()
                    + " | "
                    + rentals[i].label()
                    + " | "
                    + rentals[i].calculateCharge(units[i])
                );
            }

        } catch (FileNotFoundException e) {
            System.out.println("File rentals.txt not found.");
        }
    }
}
