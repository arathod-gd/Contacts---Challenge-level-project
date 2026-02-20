package org.contacts.stage4.models;

import java.util.Arrays;
import java.util.List;

public class Person extends Contact {

    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    public Person(String name, String surname) {
        super();   // ✅ no boolean anymore
        this.name = name;
        this.surname = surname;
        this.birthDate = "[no data]";
        this.gender = "[no data]";
    }

    // =========================
    // Polymorphism Methods
    // =========================

    @Override
    public List<String> getEditableFields() {
        return Arrays.asList("name", "surname", "birth", "gender", "number");
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name":
                name = value;
                break;
            case "surname":
                surname = value;
                break;
            case "birth":
                birthDate = value;
                break;
            case "gender":
                gender = value;
                break;
            case "number":
                number = value;
                break;
        }
        touch(); // ✅ update last edit time
    }

    @Override
    public String getField(String field) {
        return switch (field) {
            case "name" -> name;
            case "surname" -> surname;
            case "birth" -> birthDate;
            case "gender" -> gender;
            case "number" -> number;
            default -> "";
        };
    }

    @Override
    public String getSearchText() {
        return (name + " " + surname + " " + birthDate + " " + gender + " " + number)
                .toLowerCase();
    }

    // =========================
    // Display Methods
    // =========================

    @Override
    public String getTitle() {
        return name + " " + surname;
    }

    @Override
    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birth date: " + birthDate);
        System.out.println("Gender: " + gender);
        System.out.println("Number: " + number);
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeLastEdit());
    }
}
