package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static final int MAX_BORROW = 2;

    public static void main(String[] args) {
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> successful = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while (scanner.hasNext()) {
            String[] request = new String[2];
            request[0] = scanner.next();
            request[1] = scanner.next();
            requests.add(request);
        }
        scanner.close();

        books.add(new String[] { "Kalkulus", "2" });
        books.add(new String[] { "Fisika", "1" });
        books.add(new String[] { "Statistika", "2" });

        for (String[] request : requests) {
            String name = request[0];

            String[] member = null;

            for (String[] data : members) {
                if (data[0].equals(name)) {
                    member = data;
                    break;
                }
            }

            if (member == null) {
                members.add(new String[] { name, "0" });
            }
        }

        queue.addAll(requests);
        
        while (!queue.isEmpty()) {
            String[] request = queue.poll();

            String name = request[0];
            String title = request[1];

            String[] book = null;

            for (String[] data : books) {
                if (data[0].equals(title)) {
                    book = data;
                    break;
                }
            }

            String[] member = null;

            for (String[] data : members) {
                if (data[0].equals(name)) {
                    member = data;
                    break;
                }
            }

            int stock = 0;

            if (book != null) {
                stock = Integer.parseInt(book[1]);
            }

            int borrowed = Integer.parseInt(member[1]);

            if (stock > 0 && borrowed < MAX_BORROW) {
                book[1] = String.valueOf(stock - 1);
                member[1] = String.valueOf(borrowed + 1);
                successful.add(request);
            } else {
                failed.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] request : successful) {
            System.out.println(request[0] + " " + request[1]);
        }

        System.out.println("\n=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(request[0] + " " + request[1]);
        }
    }
}
