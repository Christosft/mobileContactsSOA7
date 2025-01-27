package gr.aueb.cf.mobilecontacts.exceptions;

public class ContactNotFoundException extends RuntimeException {


    public ContactNotFoundException(String message) {
        super(message);
    }
}
