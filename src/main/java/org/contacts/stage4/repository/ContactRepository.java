package org.contacts.stage4.repository;

import org.contacts.stage4.models.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ContactRepository implements Serializable {

    private List<Contact> contacts = new ArrayList<>();

    // =========================
    // Basic CRUD
    // =========================

    public void save(Contact c) {
        contacts.add(c);
    }

    public Contact findByIndex(int index) {
        if (index < 1 || index > contacts.size()) return null;
        return contacts.get(index - 1);
    }

    public void delete(int index) {
        if (index >= 1 && index <= contacts.size()) {
            contacts.remove(index - 1);
        }
    }

    public List<Contact> findAll() {
        return contacts;
    }

    public int count() {
        return contacts.size();
    }

    // =========================
    // 🔥 SEARCH (Regex + Case Insensitive)
    // =========================

    public List<Contact> search(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        List<Contact> results = new ArrayList<>();

        for (Contact c : contacts) {
            if (pattern.matcher(c.getSearchText()).find()) {
                results.add(c);
            }
        }

        return results;
    }

    // =========================
    // 🔥 FILE SAVE
    // =========================

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // =========================
    // 🔥 FILE LOAD
    // =========================

    public static ContactRepository loadFromFile(String fileName) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            return (ContactRepository) ois.readObject();
        } catch (Exception e) {
            return new ContactRepository(); // if file not found or error
        }
    }
}

