package org.contacts.stage3.service;

import org.contacts.stage3.models.*;
import org.contacts.stage3.repository.ContactRepository;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ContactService {

    private final ContactRepository repo = new ContactRepository();

    public void addPerson(Person p) { repo.save(p); }

    public void addOrganization(Organization o) { repo.save(o); }

    public Contact getByIndex(int index) { return repo.findByIndex(index); }

    public void remove(int index) { repo.delete(index); }

    public void removeByObject(Contact c) { repo.delete(c); }

    public List<Contact> list() { return repo.findAll(); }

    public int count() { return repo.count(); }

    public boolean setNumber(Contact c, String number) {
        if (!isValidPhone(number)) {
            System.out.println("Wrong number format!");
            return false;
        }
        c.setNumber(number);
        return true;
    }

    // Search method
    public List<Contact> search(String query) {
        String q = query.toLowerCase();
        return repo.findAll().stream()
                .filter(c -> c.getTitle().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    // EXACT regex from Hyperskill solution
    private boolean isValidPhone(String phone) {
        String regex = "\\+?([\\da-zA-Z]+([ -]\\([\\da-zA-Z]{2,}\\))?|\\([\\da-zA-Z]+\\))([ -][\\da-zA-Z]{2,})*";
        return Pattern.matches(regex, phone);
    }
}
