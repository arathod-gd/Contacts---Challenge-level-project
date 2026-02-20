package org.contacts.stage4.models;

import java.util.Arrays;
import java.util.List;

public class Organization extends Contact {

    private String orgName;
    private String address;

    public Organization(String orgName, String address) {
        super();   // ✅ no boolean anymore
        this.orgName = orgName;
        this.address = address;
    }

    // =========================
    // Polymorphism Methods
    // =========================

    @Override
    public List<String> getEditableFields() {
        return Arrays.asList("name", "address", "number");
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name":
                orgName = value;
                break;
            case "address":
                address = value;
                break;
            case "number":
                number = value;
                break;
        }
        touch();  // ✅ update last edit time
    }

    @Override
    public String getField(String field) {
        return switch (field) {
            case "name" -> orgName;
            case "address" -> address;
            case "number" -> number;
            default -> "";
        };
    }

    @Override
    public String getSearchText() {
        return (orgName + " " + address + " " + number).toLowerCase();
    }

    // =========================
    // Display Methods
    // =========================

    @Override
    public String getTitle() {
        return orgName;
    }

    @Override
    public void printInfo() {
        System.out.println("Organization name: " + orgName);
        System.out.println("Address: " + address);
        System.out.println("Number: " + number);
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeLastEdit());
    }
}
