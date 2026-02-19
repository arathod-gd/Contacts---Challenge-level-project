package temp3;

public class Person extends Record {

    private String name;
    private String surname;
    private String birthDate = "[no data]";
    private String gender = "[no data]";

    public Person(String name, String surname) {
        super();
        this.name = name;
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        if (birthDate.isEmpty()) {
            System.out.println("Bad birth date!");
            this.birthDate = "[no data]";
        } else {
            this.birthDate = birthDate;
        }
    }

    public void setGender(String gender) {
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        } else {
            this.gender = gender;
        }
    }

    @Override
    public void edit() {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = Main.sc.nextLine();

        switch (field) {
            case "name" -> {
                System.out.print("Enter name: ");
                name = Main.sc.nextLine();
            }
            case "surname" -> {
                System.out.print("Enter surname: ");
                surname = Main.sc.nextLine();
            }
            case "birth" -> {
                System.out.print("Enter birth date: ");
                setBirthDate(Main.sc.nextLine());
            }
            case "gender" -> {
                System.out.print("Enter gender: ");
                setGender(Main.sc.nextLine());
            }
            case "number" -> {
                System.out.print("Enter number: ");
                setNumber(Main.sc.nextLine());
            }
        }
        System.out.println("The record updated!");
    }

    @Override
    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birth date: " + birthDate);
        System.out.println("Gender: " + gender);
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + getCreatedTime());
        System.out.println("Time last edit: " + getLastEditTime());
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
