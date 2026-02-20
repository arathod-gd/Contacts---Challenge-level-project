package org.contacts.stage3.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Contact {
    protected String number;
    protected final boolean isPerson;
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeLastEdit;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected Contact(boolean isPerson) {
        this.isPerson = isPerson;
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
        this.number = "[no number]";
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setNumber(String number) {
        this.number = number;
        touch();
    }

    public String getNumber() {
        return number;
    }

    protected void touch() {
        this.timeLastEdit = LocalDateTime.now();
    }

    // formatted getters
    public String getTimeCreated() {
        return timeCreated.format(formatter);
    }

    public String getTimeLastEdit() {
        return timeLastEdit.format(formatter);
    }

    public abstract String getTitle();   // for list()
    public abstract void printInfo();    // for info
}