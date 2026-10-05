package lw03.unguided;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> enrollment = new LinkedHashMap<>();
        List<String> checkResult = new ArrayList<>();
        int rejected = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            String operation = parts[0];
            String courseCode = parts[1];

            if (operation.equals("REGISTER")) {
                int count = Integer.parseInt(parts[2]);
                
                if (count <= 0) {
                    rejected++;
                } else if (enrollment.containsKey(courseCode)) {
                    int currentCount = enrollment.get(courseCode);
                    enrollment.put(courseCode, currentCount + count);
                } else {
                    enrollment.put(courseCode, count);
                }
            } else if (operation.equals("WITHDRAW")) {
                int count = Integer.parseInt(parts[2]);

                if (count <= 0) {
                    rejected++;
                } else if (enrollment.containsKey(courseCode) && enrollment.get(courseCode) >= count) {
                    int currentCount = enrollment.get(courseCode);
                    enrollment.put(courseCode, currentCount - count);
                } else {
                    rejected++;
                }
            } else if (operation.equals("CHECK")) {
                if (enrollment.containsKey(courseCode)) {
                    checkResult.add(courseCode + ": " + enrollment.get(courseCode) + " students");
                } else {
                    checkResult.add(courseCode + ": Not found");
                }
            }
        }

        sc.close();

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResult) {
            System.out.println(result);
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");
        for (String courseCode : enrollment.keySet()) {
            System.out.println(courseCode + ": " + enrollment.get(courseCode) + " students");
        }

        System.out.println("Rejected operations: " + rejected);
    }
}
