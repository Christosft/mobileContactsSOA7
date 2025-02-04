package gr.aueb.cf.mobilecontacts;

import gr.aueb.cf.mobilecontacts.controller.MobileContactController;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;


import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final MobileContactController controller = new MobileContactController();
    public static void main(String[] args) {
        String choice;

        while (true) {
            printMenu();
            choice = getToken();

            if (choice.equals("q") || (choice.equals("Q"))) {
                break;
            }

            handleChoice(choice);
        }

        System.out.println("Thank you for using Mobile Contacts app");
    }

    public static void handleChoice(String choice) {
        String firstname;
        String lastname;
        String phoneNumber;
        String response;

        switch (choice) {
            case "1":
                System.out.println("Please insert firstname, lastname and phone number");
                firstname = getToken();
                lastname = getToken();
                phoneNumber = getToken();
                MobileContactInsertDTO insertDTO = new MobileContactInsertDTO(firstname, lastname, phoneNumber);
                response = controller.insertContact(insertDTO);

                if (response.startsWith("OK")) {
                    System.out.println("\nInput succeeded");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("\nInput not succeeded");
                    System.out.println(response.substring(6));
                }
                break;

            case "2":

                System.out.println("Εισάγετε Αριθμό Τηλεφώνου");
                phoneNumber = getToken();
                response = controller.getContactByPhoneNumber(phoneNumber);
                if (response.startsWith("Error")) {
                    System.out.println("Η επαφή δεν βρέθηκε.");
                    System.out.println(response.substring(3));
                    return;
                }
                System.out.println("Ανεπιτυχής Εισαγωγή");
                System.out.println(response.substring(6));
                System.out.println("Εισάγετε το υπάρχον ID");
                long oldId = Long.parseLong(getToken());
                System.out.println("Παρακαλώ εισάγετε νέο όνομα");
                firstname = getToken();
                System.out.println("Παρακαλώ εισάγετε νέο επώνυμο");
                lastname = getToken();
                System.out.println("Παρακαλώ εισάγετε νέο τηλεφωνικό αριθμό");
                phoneNumber = getToken();
                MobileContactUpdateDTO mobileContactUpdateDTO = new MobileContactUpdateDTO(oldId, firstname, lastname, phoneNumber);
                response = controller.updateContact(mobileContactUpdateDTO);
                System.out.println(response);

                /*System.out.println("Please enter the contact ID to update:");
                long contactId = Long.parseLong(getToken());

                System.out.println("Contact ID to update: " + contactId);

                System.out.println("Please please enter new firstname lastname and phone number to update");
                firstname = getToken();
                lastname = getToken();
                phoneNumber = getToken();
                MobileContactUpdateDTO updateDTO = new MobileContactUpdateDTO(contactId, firstname, lastname, phoneNumber);
                response = controller.updateContact(updateDTO);

                if (response.startsWith("OK")) {
                    System.out.println("\nUpdate succeeded");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("\nUpdate not succeeded");
                    System.out.println(response.substring(6));
                }
                break;*/

            case "3":
                System.out.println("Εισάγετε Κωδικό Επαφής");
                Long id = Long.parseLong(getToken());
                response = controller.deleteContactById(id);
                if (response.startsWith("OK")) {
                    System.out.println("Επιτυχής Διαγραφή");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("Ανεπιτυχής Διαγραφή");
                    System.out.println(response.substring(6));
                }
                break;
                /*System.out.println("Please insert contact phone number to delete");
                phoneNumber = getToken();
                response = controller.deleteContactByPhoneNumber(phoneNumber);

                if (response.startsWith("OK")) {
                    System.out.println("\nDelete succeeded");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("\nDelete is not succeeded");
                    System.out.println(response.substring(6));
                }
                break;*/

            case "4":
                System.out.println("Εισάγετε Κωδικό Επαφής");
                id =  Long.parseLong(getToken());
                response = controller.getContactById(id);
                if (response.startsWith("OK")) {
                    System.out.println("Επιτυχής Αναζήτηση");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("Ανεπιτυχής Αναζήτηση");
                    System.out.println(response.substring(6));
                }
                /*System.out.println("Please select contact by phone Number");
                phoneNumber = getToken();

                response = controller.getContactByPhoneNumber(phoneNumber);

                if (response.startsWith("OK")) {
                    System.out.println("\nContact search succeeded");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("\nContact search is not succeeded");
                    System.out.println(response.substring(6));
                }
                break;*/


            case "5":
                List<String > mobileContacts = controller.getAllContacts();
                if (mobileContacts.isEmpty()) System.out.println("Κενή λίστα επαφών");
                mobileContacts.forEach(System.out::println);
                break;
                /*System.out.println("Phone book:");
                List<String> contacts = controller.getAllContacts();
                if (contacts.isEmpty()) {
                    System.out.println("No contacts available.");
                } else {
                    for (String contact : contacts) {
                        System.out.println(contact);
                    }
                }
                break;*/

            default:
                //
                break;
        }
    }

    public static void printMenu() {
        System.out.println("Make a choice:");
        System.out.println("1. Insert contact");
        System.out.println("2. Update contact");
        System.out.println("3. Delete contact");
        System.out.println("4. Search contact");
        System.out.println("5. Phone Book");
        System.out.println("Q/q. Exit");
    }

    public static String getToken() {
        return in.nextLine().trim();
    }
}
