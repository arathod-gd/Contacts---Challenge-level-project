package org.contacts.stage4;

import org.contacts.stage4.controller.ContactController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 🔥 Command-line file support
        String fileName = args.length > 0 ? args[0] : null;

        ContactController controller = new ContactController(fileName);

        while (true) {

            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String action = sc.nextLine().toLowerCase();

            switch (action) {
                case "add" -> controller.add();
                case "list" -> controller.list();
                case "search" -> controller.search();
                case "count" -> controller.count();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
