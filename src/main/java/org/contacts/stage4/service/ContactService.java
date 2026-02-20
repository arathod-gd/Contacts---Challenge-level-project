package org.contacts.stage4.service;

import org.contacts.stage4.models.Contact;
import org.contacts.stage4.models.Organization;
import org.contacts.stage4.models.Person;
import org.contacts.stage4.repository.ContactRepository;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ContactService {

    private final ContactRepository repo;
    private final String fileName;

    // Constructor Injection (required for file loading)
    public ContactService(ContactRepository repo, String fileName) {
        this.repo = repo;
        this.fileName = fileName;
    }

    // =========================
    // Autosave
    // =========================

    private void autoSave() {
        if (fileName != null) {
            repo.saveToFile(fileName);
        }
    }

    // =========================
    // CRUD
    // =========================

    public void addPerson(Person p) {
        repo.save(p);
        autoSave();
    }

    public void addOrganization(Organization o) {
        repo.save(o);
        autoSave();
    }

    public Contact getByIndex(int index) {
        return repo.findByIndex(index);
    }

    public void remove(int index) {
        repo.delete(index);
        autoSave();
    }

    public List<Contact> list() {
        return repo.findAll();
    }

    public int count() {
        return repo.count();
    }

    // =========================
    // Number Validation
    // =========================

    public boolean setNumber(Contact c, String number) {
        if (!isValidPhone(number)) {
            System.out.println("Wrong number format!");
            c.setField("number", "[no number]");
            autoSave();
            return false;
        }
        c.setField("number", number);
        autoSave();
        return true;
    }

    // =========================
    // Regex Search (Stage 3 Required)
    // =========================

    public List<Contact> search(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);

        return repo.findAll().stream()
                .filter(c -> pattern.matcher(c.getSearchText()).find())
                .collect(Collectors.toList());
    }

    // =========================
    // Add Person via Scanner
    // =========================

    public void addPerson(Scanner sc) {
        System.out.print("Enter the name: ");
        String name = sc.nextLine();

        System.out.print("Enter the surname: ");
        String surname = sc.nextLine();

        Person p = new Person(name, surname);

        System.out.print("Enter the birth date: ");
        String birth = sc.nextLine();
        if (!birth.isBlank()) {
            p.setField("birth", birth);
        }

        System.out.print("Enter the gender (M, F): ");
        String gender = sc.nextLine();
        if (!gender.isBlank()) {
            p.setField("gender", gender);
        }

        System.out.print("Enter the number: ");
        String number = sc.nextLine();
        setNumber(p, number);

        addPerson(p);

        System.out.println("The record added.");
    }

    // =========================
    // Add Organization via Scanner
    // =========================

    public void addOrganization(Scanner sc) {
        System.out.print("Enter the organization name: ");
        String name = sc.nextLine();

        System.out.print("Enter the address: ");
        String address = sc.nextLine();

        Organization o = new Organization(name, address);

        System.out.print("Enter the number: ");
        String number = sc.nextLine();
        setNumber(o, number);

        addOrganization(o);

        System.out.println("The record added.");
    }

    // =========================
    // Phone Validation (Hyperskill Regex)
    // =========================

    private boolean isValidPhone(String phone) {
        String regex = "\\+?([\\da-zA-Z]+([ -]\\([\\da-zA-Z]{2,}\\))?|\\([\\da-zA-Z]+\\))([ -][\\da-zA-Z]{2,})*";
        return Pattern.matches(regex, phone);
    }


}
