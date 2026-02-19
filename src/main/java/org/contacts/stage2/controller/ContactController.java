package org.contacts.stage2.controller;

import java.util.Scanner;
import org.contacts.stage2.service.ContactService;

public class ContactController {
    public static ContactService service = new ContactService();
    public void start(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            String action = sc.nextLine().toLowerCase();

            switch (action) {
                case "add" -> service.add();
                case "remove" -> service.remove();
                case "edit" -> service.edit();
                case "count" -> service.count();
                case "list" -> service.list();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
