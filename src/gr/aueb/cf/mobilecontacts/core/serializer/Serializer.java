package gr.aueb.cf.mobilecontacts.core.serializer;

import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;

public class Serializer {


    /**
     * No instances of this class should be available
     */
    private Serializer() {

    }

    public static String serializedDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Firstname: " + readOnlyDTO.getFirstname()
                + ", Lastname: " + readOnlyDTO.getLastname() + ", Phone number: " +readOnlyDTO.getPhoneNumber();
    }
}
