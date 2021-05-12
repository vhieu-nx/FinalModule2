package manager;

import model.CheckInformation;
import model.Contact;
import storage.ReaderWriteFileCSV;
import view.DisplayMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    ReaderWriteFileCSV readerWriteFileCSV = new ReaderWriteFileCSV();
    public  void addContact(ArrayList<Contact> arrayList){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Contact FullName: ");
        String fullname = scanner.nextLine();
        System.out.println(" Contact PhoneNumber( PhoneNumber includes 10 numbers : ");
        String phone = "";
        int phoneNumber = 0;
        do {
            System.out.println("Enter Contact Phone: ");
            phone = new Scanner(System.in).nextLine();
            phoneNumber = Integer.parseInt(phone);
        }while (!phone.matches(CheckInformation.CHECK_PHONE));
        System.out.println("Enter Contact Group: ");
        String group = new Scanner(System.in).nextLine();
        System.out.println("Enter Contact Gender: ");
        String gender = new Scanner(System.in).nextLine();
        System.out.println("Enter Contact Address: ");
        String address = new Scanner(System.in).nextLine();
        String email = "";
        do {
            System.out.println("Enter Contact Email: ");
            email = new Scanner(System.in).nextLine();
        }while (!email.matches(String.valueOf(CheckInformation.CHECK_GMAIL)));
        System.out.println("Enter Date of Birth: ");
        String  dateOfBirth = new Scanner(System.in).nextLine();
        System.out.println("!!!!!!!!!!!!!!-----------------!!!!!!!!!");
        String result  = this.checkInformation(fullname,phoneNumber,group,gender,address,email,dateOfBirth);
        if (result.equals("AddNew Susscessfully")){
            Contact contact = new Contact(fullname,phoneNumber,group,gender,address,email,dateOfBirth);
            arrayList.add(contact);
            readerWriteFileCSV.writeFile(arrayList);
        }else {
            System.out.println(result);
            this.addContact(arrayList);
        }
        DisplayMenu.displayMenu();
        

        //private String fullName;
        //    private int phoneNumber;
        //    private String group;
        //    private String gender;
        //    private String address;
        //    private String mail;
        //    private String dateOfBirth;
    }
    public String checkInformation(String fullname, int phoneNumber, String group, String gender, String address, String email, String dateOfBirth) {
        String result = "";
        if (fullname.equals("") || group.equals("") || gender.equals("") || address.equals("") || dateOfBirth.equals("")){
            result = "Do not leave the field blank. Please enter full information";
        }else if (!CheckInformation.checkPhone(String.valueOf(phoneNumber))){
            result = "Input wrong phone the format";
        }else if (!CheckInformation.checkEmail(email)){
            result = "Input wrong phone the format";
        }else {
            result = "AddNew Susscessfully";
        }
        return  result;
    }
    public void editContact(int phone, ArrayList<Contact> arrayList){
        Scanner scanner = new Scanner(System.in);
        if (this.checkPhone(phone,arrayList) != -1){
            System.out.println("Found phone number, pls enter information new COntact");
            int index = this.checkPhone(phone,arrayList);
            System.out.println("Enter FullName New: ");
            String fullName = scanner.nextLine();
            System.out.println("Phone Number: ");
            int phoneNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Group New: ");
            String group = scanner.nextLine();
            System.out.println("Enter Gender New: ");
            String gender = scanner.nextLine();
            System.out.println("Enter Address New: ");
            String address = scanner.nextLine();
            System.out.println("Enter Mail New: ");
            String mail = scanner.nextLine();
            System.out.println("Enter Year Of Birth New: ");
            String dateOfBirth = scanner.nextLine();
            String result = this.checkInformation(fullName, phoneNumber, group, gender, address, mail, dateOfBirth);
            if (result.equals("AddNew Susscessfully")){
                System.out.println("Update successfully");
                Contact contact = new Contact(fullName, phoneNumber, group, gender, address, mail, dateOfBirth);
                arrayList.set(index,contact);
                readerWriteFileCSV.writeFile(arrayList);

            }else {
                System.out.println(result);
            }

        }
        else {
            System.out.println("Do not find phone");
            System.out.println("Invite you to re-enter");
            int checkPhone = new Scanner(System.in).nextInt();
            if (checkPhone != 0){
                this.editContact(checkPhone,arrayList);
            }
            else {
                DisplayMenu.displayMenu();
            }
        }
    }

    public int checkPhone(int phoneNumber, ArrayList<Contact> arrayList) {
        int index = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getPhoneNumber() == phoneNumber){
                index = i;
                break;
            }
        }
        return index;
    }
    public void deleteContact(int phone, ArrayList<Contact> arrayList){
        if (checkPhone(phone,arrayList) != -1){
            System.out.println("Do you want to delete( Enter Y to delete or enter any key to exit )");
            String yes = new Scanner(System.in).nextLine();
            if (yes.equals("Y")){
                int index = checkPhone(phone, arrayList);
                arrayList.remove(index);
                readerWriteFileCSV.writeFile(arrayList);
                System.out.println("Delete susscessfully");

            }else {

            }
        }else {
            System.out.println("Do not find to phone ");
            System.out.println("Invite phone to re-enter ");
            int phoneNumber  = new Scanner(System.in).nextInt();
            if (phoneNumber != 0){
                deleteContact(phoneNumber,arrayList);
            }else {

            }
        }
    }
    public void searchContactByNameorPhone(ArrayList<Contact> arrayList){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the search function: ");
        System.out.println("1.Search by phone number");
        System.out.println("2.Search by name");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            System.out.println("Please Enter your  phone number want to search");
            int phoneSearch = Integer.parseInt(scanner.nextLine());
            if (checkPhone(phoneSearch, arrayList) != -1) {
                int indexPhone = checkPhone(phoneSearch, arrayList);
                displayContact(indexPhone, arrayList);
            } else System.out.println("Do not  find data");
        }else if (choice == 2) {
            System.out.println("Please Enter your name you want to search");
            String nameSearch = scanner.nextLine();
            if (checkName(nameSearch, arrayList) != -1) {
                int indexName = checkName(nameSearch, arrayList);
                displayContact(indexName, arrayList);
            } else System.out.println("Do not find data");
        }
    }



    public void displayContact(int index, ArrayList<Contact> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("--------------------------------------");
            System.out.println("Full name: " + arrayList.get(index).getFullName());
            System.out.println("Phone number: " + arrayList.get(index).getPhoneNumber());
            System.out.println("Gender: " + arrayList.get(index).getGender());
            System.out.println("Group: " + arrayList.get(index).getGroup());
            System.out.println("Address: " + arrayList.get(index).getAddress());
            System.out.println("--------------------------------------");
        }

        DisplayMenu.displayMenu();
    }
    public int checkName(String nameSearch, ArrayList<Contact> arrayList) {
        int index = -1;
        for (int i = 0; i <arrayList.size() ; i++) {
            if (arrayList.get(i).getFullName() == nameSearch){
                index = i;
                break;
            }
        }
        return index;
    }

    public void displayAllContact(ArrayList<Contact> contacts) {
        for (int i = 0; i <contacts.size() ; i++) {
            System.out.println("--------------------------------------");
            System.out.println("Full name: " + contacts.get(i).getFullName());
            System.out.println("Phone number: " + contacts.get(i).getPhoneNumber());
            System.out.println("Gender: " + contacts.get(i).getGender());
            System.out.println("Group: " + contacts.get(i).getGroup());
            System.out.println("Address: " + contacts.get(i).getAddress());
            System.out.println("Email: " + contacts.get(i).getMail());
            System.out.println("DateOfBirth: " + contacts.get(i).getDateOfBirth());
            System.out.println("--------------------------------------");
        }
        DisplayMenu.displayMenu();
    }
}
