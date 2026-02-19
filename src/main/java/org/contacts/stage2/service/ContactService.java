package org.contacts.stage2.service;

import java.util.Scanner;
import org.contacts.stage2.models.Contacts;

import static org.contacts.stage2.repository.ContactRepository.phoneBook;


public class ContactService {
    public static Scanner sc = new Scanner(System.in);

    public static void add(){
        System.out.print("Enter the name: ");
        String name = sc.nextLine();

        System.out.print("Enter the surname: ");
        String surname = sc.nextLine();

        System.out.print("Enter the number: ");
        String number = sc.nextLine();

        phoneBook.add(new Contacts(name, surname, number));
        System.out.println("The record added.");
    }

    public static void remove(){
        if(phoneBook.isEmpty()){
            System.out.println("No records to remove!");
            return;
        }

        list();
        System.out.print("Select a record: ");
        int record = sc.nextInt();
        sc.nextLine();

        phoneBook.remove(record - 1);   // remove by index
        System.out.println("The record removed!");
    }

    public static void edit(){
        if(phoneBook.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        list();
        System.out.print("Select a record: ");
        int record = sc.nextInt();
        sc.nextLine();

        Contacts contact = phoneBook.get(record - 1);

        System.out.print("Select a field (name, surname, number): ");
        String field = sc.nextLine().toLowerCase();

        switch (field){

            case "name" -> {
                System.out.print("Enter name: ");
                contact.setName(sc.nextLine());
            }

            case "surname" -> {
                System.out.print("Enter surname: ");
                contact.setSurname(sc.nextLine());
            }

            case "number" -> {
                System.out.print("Enter number: ");
                contact.setNumber(sc.nextLine());
            }

            default -> {
                System.out.println("Invalid field");
                return;
            }
        }

        System.out.println("The record updated!");
    }

    public static void count(){
        System.out.println("The Phone Book has " + phoneBook.size() + " records.");
    }

    public static void list() {
        if(phoneBook.isEmpty()){
            System.out.println("No records");
            return;
        }

        for(int i = 0; i < phoneBook.size(); i++){
            System.out.println((i + 1) + ". " + phoneBook.get(i));
        }
    }
}
