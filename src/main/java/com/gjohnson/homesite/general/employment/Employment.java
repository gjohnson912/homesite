package com.gjohnson.homesite.general.employment;

import com.gjohnson.homesite.general.establishment.Establishment;

import java.time.LocalDate;

public class Employment {

    private Integer employmentId;
    private Establishment company;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;

    public Integer getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Integer employmentId) {
        this.employmentId = employmentId;
    }

    public Establishment getCompany() {
        return company;
    }

    public void setCompany(Establishment company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
