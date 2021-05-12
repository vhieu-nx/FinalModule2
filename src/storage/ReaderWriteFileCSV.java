package storage;

import model.Contact;

import java.io.*;
import java.util.ArrayList;

public class ReaderWriteFileCSV {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR ="\n" ;

    public void writeFile(ArrayList<Contact> arrayList){
        File file = new File("data/contacts.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < arrayList.size(); i++) {
                String name = arrayList.get(i).getFullName();
                int phoneNumber = arrayList.get(i).getPhoneNumber();
                String group = arrayList.get(i).getGroup();
                String gender = arrayList.get(i).getGender();
                String address = arrayList.get(i).getAddress();
                String gmail = arrayList.get(i).getMail();
                String dateOfBirth = arrayList.get(i).getDateOfBirth();
                //private String fullName;
                //    private int phoneNumber;
                //    private String group;
                //    private String gender;
                //    private String address;
                //    private String mail;
                //    private String dateOfBirth;
                String line = name + COMMA_DELIMITER + phoneNumber + COMMA_DELIMITER + group + COMMA_DELIMITER
                        + gender + COMMA_DELIMITER + address + COMMA_DELIMITER + gmail + COMMA_DELIMITER
                        + dateOfBirth + NEW_LINE_SEPARATOR;
                bufferedWriter.write(line);
            }
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Write File CSV Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Contact> readFile(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try {
            FileReader fileReader = new FileReader("data/contacts.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                Contact contact = splitString(line);
                contacts.add(contact);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public static Contact splitString(String line) {
        String[] split = line.split(COMMA_DELIMITER);
        String fullName = split[0];
        int phoneNumber = Integer.parseInt(split[1]);
        String group  = split[2];
        String gender = split[3];
        String address = split[4];
        String gmail = split[5];
        String dateOfBirth = split[6];
        Contact contact = new Contact(fullName,phoneNumber,group,gender,address,gmail,dateOfBirth);
        return contact;
        //private String fullName;
        //    private int phoneNumber;
        //    private String group;
        //    private String gender;
        //    private String address;
        //    private String mail;
        //    private String dateOfBirth;
    }
}
