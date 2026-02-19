package org.contacts.stage2;

import org.contacts.stage2.controller.ContactController;
import java.util.Scanner;

public class Main {
    public static ContactController controller = new ContactController();

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        controller.start();
    }
}
