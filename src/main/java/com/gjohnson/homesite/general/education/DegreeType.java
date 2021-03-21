package com.gjohnson.homesite.general.education;

public class DegreeType {

    private Integer degreeTypeId;
    private String name;
    private String acronym;
    private String displayName;
    private String description;

    public Integer getDegreeTypeId() {
        return degreeTypeId;
    }

    public void setDegreeTypeId(Integer degreeTypeId) {
        this.degreeTypeId = degreeTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
