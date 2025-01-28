package gr.aueb.cf.mobilecontacts.controller;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDAOImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.IMobileContactService;
import gr.aueb.cf.mobilecontacts.service.MobileContactServiceImpl;
import gr.aueb.cf.mobilecontacts.validation.ValidationUtil;

public class MobileContactController {

    private final IMobileContactDAO dao = new MobileContactDAOImpl();
    private final IMobileContactService service = new MobileContactServiceImpl(dao);

    public String insertContact(MobileContactInsertDTO insertDTO) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            // Validate input data(dto)
            String errorVector = ValidationUtil.validateDTO(insertDTO);
            if (!errorVector.isEmpty()) {
                return "Error." + "Validation error\n" + errorVector;
            }

            // If validation is ok, insert contact
            mobileContact = service.insertMobileContact(insertDTO);
            readOnlyDTO = mapMobileContactToDto(mobileContact);
            return "OK\n" + serializedDTO(readOnlyDTO);

        } catch (PhoneNumberAlreadyExistsException e) {
            return "Error\n" + e.getMessage() + "\n";
        }
    }

    private MobileContactReadOnlyDTO mapMobileContactToDto(MobileContact mobileContact) {
        return new MobileContactReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstname(), mobileContact.getLastname(),
                mobileContact.getPhoneNumber());
    }

    private String serializedDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Firstname: " + readOnlyDTO.getFirstname()
                + ", Lastname: " + readOnlyDTO.getLastname() + ", Phone number: " +readOnlyDTO.getPhoneNumber();
    }
}
