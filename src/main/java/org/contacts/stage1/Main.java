package org.contacts.stage1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.contacts.stage1.models.Contacts;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contacts> phoneBook = new ArrayList<>();

        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();

        System.out.println("Enter the surname of the person:");
        String lastname = scanner.nextLine();

        System.out.println("Enter the number:");
        String number = scanner.nextLine();

        phoneBook.add(new Contacts(name, lastname, number));

        System.out.println("A record created!");
        System.out.println("A Phone Book with a single record created!");
    }
}