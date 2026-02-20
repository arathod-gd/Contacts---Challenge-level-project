package org.contacts.stage4.controller;

import org.contacts.stage4.models.Contact;
import org.contacts.stage4.repository.ContactRepository;
import org.contacts.stage4.service.ContactService;

import java.util.List;
import java.util.Scanner;

public class ContactController {

    private final Scanner sc = new Scanner(System.in);
    private final ContactService service;

    public ContactController(String fileName) {

        ContactRepository repo =
                fileName != null
                        ? ContactRepository.loadFromFile(fileName)
                        : new ContactRepository();

        this.service = new ContactService(repo, fileName);
    }

    // =========================
    // ADD
    // =========================

    public void add() {
        System.out.print("Enter the type (person, organization): ");
        String type = sc.nextLine();

        if (type.equalsIgnoreCase("person")) {
            service.addPerson(sc);
        } else if (type.equalsIgnoreCase("organization")) {
            service.addOrganization(sc);
        } else {
            System.out.println("Invalid type.");
        }
    }

    // =========================
    // LIST
    // =========================

    public void list() {
        List<Contact> list = service.list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getTitle());
        }

        if (list.isEmpty()) return;

        System.out.print("[list] Enter action ([number], back): ");
        String action = sc.nextLine();

        if (action.equalsIgnoreCase("back")) return;

        try {
            int idx = Integer.parseInt(action);
            Contact c = service.getByIndex(idx);
            if (c != null) recordMenu(c);
        } catch (NumberFormatException ignored) {}
    }

    // =========================
    // COUNT
    // =========================

    public void count() {
        System.out.println("The Phone Book has " + service.count() + " records.");
    }

    // =========================
    // SEARCH
    // =========================

    public void search() {

        while (true) {
            System.out.print("Enter search query: ");
            String query = sc.nextLine();

            List<Contact> results = service.search(query);

            System.out.println("Found " + results.size() + " result(s):");

            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).getTitle());
            }

            System.out.print("[search] Enter action ([number], back, again): ");
            String action = sc.nextLine();

            if (action.equalsIgnoreCase("back")) return;
            if (action.equalsIgnoreCase("again")) continue;

            try {
                int idx = Integer.parseInt(action);
                if (idx >= 1 && idx <= results.size()) {
                    recordMenu(results.get(idx - 1));
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    // =========================
    // RECORD MENU
    // =========================

    private void recordMenu(Contact c) {

        while (true) {
            c.printInfo();

            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = sc.nextLine();

            if (action.equalsIgnoreCase("edit")) {
                editRecord(c);
            } else if (action.equalsIgnoreCase("delete")) {
                service.remove(service.list().indexOf(c) + 1);
                System.out.println("The record removed!");
                return;
            } else if (action.equalsIgnoreCase("menu")) {
                return;
            }
        }
    }

    // =========================
    // EDIT (Fully Polymorphic)
    // =========================

    private void editRecord(Contact c) {

        System.out.println("Select a field " + c.getEditableFields() + ": ");
        String field = sc.nextLine();

        if (!c.getEditableFields().contains(field)) {
            System.out.println("Invalid field.");
            return;
        }

        System.out.print("Enter value: ");
        String value = sc.nextLine();

        if (field.equals("number")) {
            service.setNumber(c, value);
        } else {
            c.setField(field, value);
        }

        System.out.println("Saved");
    }
}
