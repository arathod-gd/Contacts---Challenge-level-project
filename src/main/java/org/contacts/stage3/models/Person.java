package org.contacts.stage3.models;

public class Person extends Contact {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    public Person(String name, String surname) {
        super(true);
        this.name = name;
        this.surname = surname;
        this.birthDate = "[no data]";
        this.gender = "[no data]";
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
