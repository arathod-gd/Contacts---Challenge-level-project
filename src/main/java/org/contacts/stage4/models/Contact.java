package org.contacts.stage4.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Contact implements Serializable {

    protected String number;
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeLastEdit;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    protected Contact() {
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
        this.number = "[no number]";
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

    public String getTimeCreated() {
        return timeCreated.format(formatter);
    }

    public String getTimeLastEdit() {
        return timeLastEdit.format(formatter);
    }

    // 🔥 POLYMORPHISM METHODS (IMPORTANT)

    // return editable fields like (name, surname, number)
    public abstract List<String> getEditableFields();

    // change field dynamically
    public abstract void setField(String field, String value);

    // get field value dynamically
    public abstract String getField(String field);

    // used for search (append all fields)
    public abstract String getSearchText();

    // for list()
    public abstract String getTitle();

    // for showing full info
    public abstract void printInfo();
}
