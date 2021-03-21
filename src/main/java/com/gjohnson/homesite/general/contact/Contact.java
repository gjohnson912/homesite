package com.gjohnson.homesite.general.contact;

public class Contact {

    private Integer contactId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private boolean systemWideDefault;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isSystemWideDefault() {
        return systemWideDefault;
    }

    public void setSystemWideDefault(boolean systemWideDefault) {
        this.systemWideDefault = systemWideDefault;
    }
}
