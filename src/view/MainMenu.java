package view;

import manager.ContactManager;
import model.Contact;
import storage.ReaderWriteFileCSV;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        ContactManager contactManager = new ContactManager();
        ReaderWriteFileCSV readerWriteFileCSV = new ReaderWriteFileCSV();
        DisplayMenu.displayMenu();
        int choice = -1;
        do {
            System.out.print("Enter choice: ");
            try {
                    choice = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println("Input the wrong");
            }
            switch (choice){
                case 1:
                    contactManager.displayAllContact(contacts);
                    break;
                case 2:
                    contactManager.addContact(contacts);
                    break;
                case 3:
                    System.out.println("Enter your phoen");
                    int phone = Integer.parseInt(scanner.nextLine());
                    contactManager.editContact(phone,contacts);
                    break;
                case 4:
                    System.out.println("Enter your phone delete: ");
                    int phoneDelete = Integer.parseInt(scanner.nextLine());
                    contactManager.deleteContact(phoneDelete, contacts);
                    break;
                case 5:
                    contactManager.searchContactByNameorPhone(contacts);
                    break;
                case 6:
                    readerWriteFileCSV.writeFile(contacts);
                    break;
                case 7:
                    contacts = readerWriteFileCSV.readFile();
                    System.out.println(contacts);
                    break;
                case 8:
                    System.exit(0);
                    break;
            }


        }while (choice != 0);
    }



}
