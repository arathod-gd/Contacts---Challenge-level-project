package org.contacts.stage3;

import org.contacts.stage3.controller.ContactController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactController controller = new ContactController();

        while (true) {
            System.out.print("\nEnter action (add, count, info, search, exit): ");
            String action = sc.nextLine().toLowerCase();

            switch (action) {
                case "add" -> controller.add();
                case "count" -> controller.count();
                case "info" -> controller.info();
                case "search" -> controller.search();
                case "exit" -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
