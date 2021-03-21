package com.gjohnson.homesite.general;

import com.gjohnson.homesite.general.contact.ContactRepository;
import com.gjohnson.homesite.general.education.EducationRepository;
import com.gjohnson.homesite.general.employment.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneralInfoService {

    private final ContactRepository contactRepository;
    private final EmploymentRepository employmentRepository;
    private final EducationRepository educationRepository;

    @Autowired
    public GeneralInfoService(ContactRepository contactRepository, EmploymentRepository employmentRepository, EducationRepository educationRepository) {
        this.contactRepository = contactRepository;
        this.employmentRepository = employmentRepository;
        this.educationRepository = educationRepository;
    }

    public Optional<GeneralInfo> retrieveSystemDefaultGeneralInfo() {
        return this.contactRepository.retrieveDefaultContact()
                .map(contact -> new GeneralInfo(contact,
                        this.employmentRepository.getEmploymentListByContact(contact.getContactId()),
                        this.educationRepository.getEducationListByContact(contact.getContactId())));
    }
}
