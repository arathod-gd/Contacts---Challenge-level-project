package temp3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Record> phoneBook = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            String action = sc.nextLine();

            switch (action) {
                case "add" -> add();
                case "remove" -> remove();
                case "edit" -> edit();
                case "count" -> System.out.println("The Phone Book has " + phoneBook.size() + " records.");
                case "info" -> info();
                case "exit" -> { return; }
            }

            System.out.println();
        }
    }

    private static void add() {
        System.out.print("Enter the type (person, organization): ");
        String type = sc.nextLine();

        if (type.equals("person")) {
            System.out.print("Enter the name: ");
            String name = sc.nextLine();

            System.out.print("Enter the surname: ");
            String surname = sc.nextLine();

            Person person = new Person(name, surname);

            System.out.print("Enter the birth date: ");
            person.setBirthDate(sc.nextLine());

            System.out.print("Enter the gender (M, F): ");
            person.setGender(sc.nextLine());

            System.out.print("Enter the number: ");
            person.setNumber(sc.nextLine());

            phoneBook.add(person);

        } else {
            System.out.print("Enter the organization name: ");
            String name = sc.nextLine();

            System.out.print("Enter the address: ");
            String address = sc.nextLine();

            Organization org = new Organization(name, address);

            System.out.print("Enter the number: ");
            org.setNumber(sc.nextLine());

            phoneBook.add(org);
        }

        System.out.println("The record added.");
    }

    private static void remove() {

        if (phoneBook.isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        list();
        System.out.print("Select a record: ");
        int index = Integer.parseInt(sc.nextLine());

        phoneBook.remove(index - 1);
        System.out.println("The record removed!");
    }


    private static void edit() {

        if (phoneBook.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        list();
        System.out.print("Select a record: ");
        int index = Integer.parseInt(sc.nextLine());

        phoneBook.get(index - 1).edit();
    }


    private static void info() {

        if (phoneBook.isEmpty()) {
            System.out.println("No records to show!");
            return;
        }

        list();
        System.out.print("Enter index to show info: ");
        int index = Integer.parseInt(sc.nextLine());

        phoneBook.get(index - 1).printInfo();
    }


    private static void list() {
        for (int i = 0; i < phoneBook.size(); i++) {
            System.out.println((i + 1) + ". " + phoneBook.get(i));
        }
    }
}
