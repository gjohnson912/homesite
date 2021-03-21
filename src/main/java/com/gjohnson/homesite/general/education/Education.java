package com.gjohnson.homesite.general.education;

import com.gjohnson.homesite.general.establishment.Establishment;

import java.time.LocalDate;

public class Education {

    private Integer educationId;
    private String name;
    private String subName;
    private EducationType educationType;
    private DegreeDetail degreeDetail;
    private Establishment institution;
    private LocalDate completionDate;

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    public DegreeDetail getDegreeDetail() {
        return degreeDetail;
    }

    public void setDegreeDetail(DegreeDetail degreeDetail) {
        this.degreeDetail = degreeDetail;
    }

    public Establishment getInstitution() {
        return institution;
    }

    public void setInstitution(Establishment institution) {
        this.institution = institution;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
