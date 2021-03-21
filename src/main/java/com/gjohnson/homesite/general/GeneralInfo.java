package com.gjohnson.homesite.general;

import com.gjohnson.homesite.general.contact.Contact;
import com.gjohnson.homesite.general.education.Education;
import com.gjohnson.homesite.general.employment.Employment;

import java.util.ArrayList;
import java.util.List;

public class GeneralInfo {

    private Contact contact;
    private List<Employment> employmentHistoryList;
    private List<Education> educationHistoryList;

    public GeneralInfo() {
        this.employmentHistoryList = new ArrayList<>();
        this.educationHistoryList = new ArrayList<>();
    }

    public GeneralInfo(Contact contact) {
        this.contact = contact;
    }

    public GeneralInfo(Contact contact, List<Employment> employmentHistoryList, List<Education> educationHistoryList) {
        this.contact = contact;
        this.employmentHistoryList = employmentHistoryList;
        this.educationHistoryList = educationHistoryList;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Employment> getEmploymentHistoryList() {
        return employmentHistoryList;
    }

    public void setEmploymentHistoryList(List<Employment> employmentHistoryList) {
        this.employmentHistoryList = employmentHistoryList;
    }

    public List<Education> getEducationHistoryList() {
        return educationHistoryList;
    }

    public void setEducationHistoryList(List<Education> educationHistoryList) {
        this.educationHistoryList = educationHistoryList;
    }
}
