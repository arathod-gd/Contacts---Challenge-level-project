package temp3;

import java.time.LocalDateTime;

public abstract class Record {

    private String number = "";
    private LocalDateTime created;
    private LocalDateTime lastEdit;

    public Record() {
        this.created = LocalDateTime.now().withSecond(0).withNano(0);
        this.lastEdit = created;
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
        updateEditTime();
    }

    private void updateEditTime() {
        lastEdit = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public String getCreatedTime() {
        return created.toString();
    }

    public String getLastEditTime() {
        return lastEdit.toString();
    }

    private boolean isValidNumber(String number) {
        if (number == null || number.isEmpty()) return false;

        if (number.chars().filter(ch -> ch == '(').count() > 1 ||
                number.chars().filter(ch -> ch == ')').count() > 1) {
            return false;
        }

        if (number.contains("(") || number.contains(")")) {
            int open = number.indexOf("(");
            int close = number.indexOf(")");
            if (open == -1 || close == -1 || close < open) return false;

            String inside = number.substring(open + 1, close);
            if (!inside.matches("[A-Za-z0-9]{2,}")) return false;
        }

        String cleaned = number.replaceAll("[()+]", "");
        String[] parts = cleaned.split("[ -]");

        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].matches("[A-Za-z0-9]+")) return false;
            if (i == 0 && parts[i].length() < 1) return false;
            if (i > 0 && parts[i].length() < 2) return false;
        }

        return true;
    }

    public abstract void edit();
    public abstract void printInfo();
}
