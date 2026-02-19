package org.contacts.stage1;

public class Contacts2 {
    private static int counter = 0;   // shared by all objects

    private String name;
    private String lastName;
    private String phone;
    private int contactID = 0;

    public Contacts2(String name, String lastName, String phone) {
        //unique contact id
        this.contactID = ++counter;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;

    }

    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public int getContactID() { return contactID; }

    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setContactID(int contactID) { this.contactID = contactID; }

    @Override
    public String toString() {
        return contactID+". "+getName()+" "+getLastName()+" "+" "+getPhone();
    }
}
