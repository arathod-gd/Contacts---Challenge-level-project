package org.contacts.stage2.models;

public class Contacts {

    private String name;
    private String surname;
    private String number = "";

    public Contacts(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        setNumber(number);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number.isEmpty() ? "[no number]" : number;
    }

    public void setNumber(String number) {
        if (isValidNumber(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
        }
    }

    public boolean hasNumber() {
        return !number.isEmpty();
    }

    private boolean isValidNumber(String number) {
        if (number == null || number.isEmpty()) return false;

        if (number.startsWith("+")) {
            number = number.substring(1);
        }

        if (number.contains("(") || number.contains(")")) {
            int open = number.indexOf("(");
            int close = number.indexOf(")");

            if (open == -1 || close == -1 || close < open) return false;
            if (number.indexOf("(", open + 1) != -1) return false;

            String inside = number.substring(open + 1, close);
            if (!inside.matches("[A-Za-z0-9]{2,}")) return false;
        }

        number = number.replace("(", "").replace(")", "");

        String[] parts = number.split("[ -]");

        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].matches("[A-Za-z0-9]+")) return false;

            if (i == 0) {
                if (parts[i].length() < 1) return false;
            } else {
                if (parts[i].length() < 2) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + getNumber();
    }
}

