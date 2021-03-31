package com.gjohnson.homesite.general;

import com.gjohnson.homesite.general.contact.ContactRepository;
import com.gjohnson.homesite.general.contact.resume.Resume;
import com.gjohnson.homesite.general.contact.resume.ResumeRepository;
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
    private final ResumeRepository resumeRepository;

    @Autowired
    public GeneralInfoService(ContactRepository contactRepository,
                              EmploymentRepository employmentRepository,
                              EducationRepository educationRepository,
                              ResumeRepository resumeRepository) {
        this.contactRepository = contactRepository;
        this.employmentRepository = employmentRepository;
        this.educationRepository = educationRepository;
        this.resumeRepository = resumeRepository;
    }

    /**
     * There is a system wide default Contact. This finds the information related to that default Contact.
     *
     * @return an {@link Optional} {@link GeneralInfo} object
     */
    public Optional<GeneralInfo> retrieveSystemDefaultGeneralInfo() {
        return this.contactRepository.retrieveDefaultContact()
                .map(contact -> new GeneralInfo(contact,
                        this.employmentRepository.getEmploymentListByContact(contact.getContactId()),
                        this.educationRepository.getEducationListByContact(contact.getContactId())));
    }

    public Optional<Resume> retrieveResumeByContactId(Integer contactId) {
        return this.resumeRepository.retrieveByContactId(contactId);
    }

    public Resume insertResume(Integer contactId, Resume resume) {
        return this.resumeRepository.createResume(contactId, resume);
    }
}
