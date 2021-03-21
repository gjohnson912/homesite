package com.gjohnson.homesite.general.education;

public class DegreeDetail {

    private Integer degreeDetailId;
    private DegreeType degreeType;
    private String major;
    private String minor;

    public Integer getDegreeDetailId() {
        return degreeDetailId;
    }

    public void setDegreeDetailId(Integer degreeDetailId) {
        this.degreeDetailId = degreeDetailId;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }
}
