package temp3;

public class Organization extends Record {

    private String name;
    private String address;

    public Organization(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }

    @Override
    public void edit() {
        System.out.print("Select a field (address, number): ");
        String field = Main.sc.nextLine();

        switch (field) {
            case "address" -> {
                System.out.print("Enter address: ");
                address = Main.sc.nextLine();
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
        System.out.println("Organization name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + getCreatedTime());
        System.out.println("Time last edit: " + getLastEditTime());
    }

    @Override
    public String toString() {
        return name;
    }
}
