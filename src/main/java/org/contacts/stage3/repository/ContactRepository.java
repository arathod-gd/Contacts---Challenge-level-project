package org.contacts.stage3.repository;

import org.contacts.stage3.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

    public void save(Contact c) { contacts.add(c); }

    public Contact findByIndex(int index) {
        if (index < 1 || index > contacts.size()) return null;
        return contacts.get(index - 1);
    }

    public void delete(int index) {
        if (index >= 1 && index <= contacts.size()) {
            contacts.remove(index - 1);
        }
    }

    public void delete(Contact c) { contacts.remove(c); }

    public List<Contact> findAll() { return contacts; }

    public int count() { return contacts.size(); }
}
