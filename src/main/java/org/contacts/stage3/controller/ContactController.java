package org.contacts.stage3.controller;

import org.contacts.stage3.models.*;
import org.contacts.stage3.service.ContactService;

import java.util.List;
import java.util.Scanner;

public class ContactController {

    private final ContactService service = new ContactService();
    private final Scanner sc = new Scanner(System.in);

    public void add() {
        System.out.print("Enter the type (person, organization): ");
        String type = sc.nextLine();

        if (type.equalsIgnoreCase("person")) {
            service.addPerson(sc);
        } else if (type.equalsIgnoreCase("organization")) {
            service.addOrganization(sc);
        } else {
            System.out.println("Error! choose correct option..");
        }
    }

    public void list() {
        List<Contact> list = service.list();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getTitle());
        }
        if (!list.isEmpty()) {
            System.out.print("[list] Enter action ([number], back): ");
            String action = sc.nextLine();
            if (action.equals("back")) return;
            try {
                int idx = Integer.parseInt(action);
                if (idx >= 1 && idx <= list.size()) recordMenu(service.getByIndex(idx));
            } catch (NumberFormatException ignored) {}
        }
    }

    public void info() {
        List<Contact> list = service.list();
        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getTitle());
        }
        System.out.print("[list] Enter action ([number], back): ");
        String action = sc.nextLine();
        if (action.equalsIgnoreCase("back")) return;
        try {
            int idx = Integer.parseInt(action);
            if (idx >= 1 && idx <= list.size()) {
                recordMenu(list.get(idx - 1));  // directly show record menu
            }
        } catch (NumberFormatException ignored) {}
    }


    public void remove() {
        list();
        System.out.print("Select a record: ");
        int idx = Integer.parseInt(sc.nextLine());
        service.remove(idx);
        System.out.println("The record removed!");
    }

    public void edit() {
        list();
        System.out.print("Select a record: ");
        int idx = Integer.parseInt(sc.nextLine());
        Contact c = service.getByIndex(idx);
        editRecord(c);
    }

    public void count() {
        System.out.println("The Phone Book has " + service.count() + " records.");
    }

    // ----------------- NEW: SEARCH -------------------
    public void search() {
        System.out.println("** write back to go back **");

        while (true) {
            System.out.print("Enter search query: ");
            String query = sc.nextLine();

            //go back
            if (query.equalsIgnoreCase("back")) {
                return;
            }

            List<Contact> results = service.search(query);
            if (results.isEmpty()) {
                System.out.println("No results found.");
            } else {
                System.out.println("Found " + results.size() + " result(s):");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println((i + 1) + ". " + results.get(i).getTitle());
                }

                System.out.print("[search] Enter action ([number], back, again): ");
                String action = sc.nextLine();

                if (action.equalsIgnoreCase("back")) return;
                else if (action.equalsIgnoreCase("again")) continue;
                else {
                    try {
                        int idx = Integer.parseInt(action);
                        if (idx >= 1 && idx <= results.size()) recordMenu(results.get(idx - 1));
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
    }

    // ----------------- RECORD MENU -------------------
    private void recordMenu(Contact c) {
        while (true) {
            c.printInfo();
            System.out.print("[record] Enter action (edit, delete, back): ");
            String action = sc.nextLine();

            if (action.equalsIgnoreCase("edit")) {
                editRecord(c);
            } else if (action.equalsIgnoreCase("delete")) {
                service.removeByObject(c);
                System.out.println("The record removed!");
                return;
            } else if (action.equalsIgnoreCase("back")) return;
        }
    }

    private void editRecord(Contact c) {
        if (c.isPerson()) {
            Person p = (Person) c;
            System.out.print("Select a field (name, surname, birth, gender, number): ");
            String field = sc.nextLine();
            System.out.print("Enter value: ");
            String val = sc.nextLine();

            switch (field) {
                case "name" -> p.setName(val);
                case "surname" -> p.setSurname(val);
                case "birth" -> p.setBirthDate(val);
                case "gender" -> p.setGender(val);
                case "number" -> service.setNumber(p, val);
            }
        } else {
            Organization o = (Organization) c;
            System.out.print("Select a field (name, address, number): ");
            String field = sc.nextLine();
            System.out.print("Enter value: ");
            String val = sc.nextLine();

            switch (field) {
                case "name" -> o.setOrgName(val);
                case "address" -> o.setAddress(val);
                case "number" -> service.setNumber(o, val);
            }
        }
        System.out.println("Saved");
    }
}
