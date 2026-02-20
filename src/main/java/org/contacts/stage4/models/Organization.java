package org.contacts.stage3.models;

public class Organization extends Contact {
    private String orgName;
    private String address;

    public Organization(String orgName, String address) {
        super(false);
        this.orgName = orgName;
        this.address = address;
    }

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
