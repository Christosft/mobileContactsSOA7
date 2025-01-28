package gr.aueb.cf.mobilecontacts.validation;

import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;

public class ValidationUtil {


    /**
     *
     */
    private ValidationUtil() {

    }

    public static String validateDTO(MobileContactInsertDTO insertDTO) {
        String errorResponse = "";

        if (insertDTO.getPhoneNumber().length() <= 5) errorResponse += "Phone number must has more than five digits.\n";
        if (insertDTO.getFirstname().length() < 2) errorResponse += "Firstname must have two or more characters.\n";
        if (insertDTO.getLastname().length() < 2) errorResponse += "Lastname must have two or more characters.\n";

        return errorResponse;
    }

    public static String validateDTO(MobileContactUpdateDTO updateDTO) {
        String errorResponse = "";

        if (updateDTO.getPhoneNumber().length() <= 5) errorResponse += "Phone number must has more than five digits.\n";
        if (updateDTO.getFirstname().length() < 2) errorResponse += "Firstname must have two or more characters\n";
        if (updateDTO.getLastname().length() < 2) errorResponse += "Lastname must have two or more characters.\n";

        return errorResponse;
    }
}