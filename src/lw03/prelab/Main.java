package lw03.prelab;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    static void problem1() {
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            Scanner lineScanner = new Scanner(line);
            String operation = lineScanner.next();

            if (operation.equals("ADD")) {
                String song = lineScanner.nextLine().trim();
                playlist.add(song);
                lineScanner.close();
            } else if (operation.equals("INSERT")) {
                int index = lineScanner.nextInt();
                String song = lineScanner.nextLine().trim();
                playlist.add(index, song);
                lineScanner.close();
            } else if (operation.equals("REMOVE")) {
                String song = lineScanner.nextLine().trim();
                playlist.remove(song);
                lineScanner.close();
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                continue;
            }

            if (participants.contains(name)) {
                duplicateCount++;
            } else {
                participants.add(name);
            }
        }
        scanner.close();

        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;
        for (String participant : participants) {
            System.out.println(number + ". " + participant);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    static void problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (scanner.hasNext()) {
            String type = scanner.next();
            String product = scanner.next();
            int quantity = scanner.nextInt();

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int stock = inventory.get(product);
                    inventory.put(product, stock + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    int stock = inventory.get(product);
                    inventory.put(product, stock - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        scanner.close();

        System.out.println();
        System.out.println("===== Problem 3 =====");
        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}
