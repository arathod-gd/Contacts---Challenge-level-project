package temp3;

public class Contacts {

    // ❌ REMOVED:
    // public static int count = 0;
    // private int id;
    // WHY? Hyperskill does NOT want ID-based logic.
    // It wants numbering based on list position.

    private String name;
    private String surname;
    private String number = "";

    public Contacts(String name, String surname, String number) {
        // ❌ REMOVED:
        // this.id = ++count;
        // WHY? ID system breaks numbering after deletion.

        this.name = name;
        this.surname = surname;
        setNumber(number);
    }

    // ❌ REMOVED:
    // public int getId()
    // WHY? We no longer use IDs.

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
            this.number = "";
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
        // ❌ BEFORE:
        // return getId()+". "+getName()+" "+getSurname()+", "+getNumber();
        // PROBLEM: ID does NOT change after deletion.

        // ✅ NOW:
        // Only print contact info.
        // Numbering is handled inside list() method.
        return name + " " + surname + ", " + getNumber();
    }
}
