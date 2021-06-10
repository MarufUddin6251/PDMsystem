package com.example.pdmsystem;

public class Upload2 {


    private String name;
    private String instituteName;
    private String subName;
    private String year;
    private String classRange;
    private String honoraryRange;
    private String contactNumber;

    public Upload2()
    {

    }
    public Upload2(String name, String instituteName, String subName, String year, String classRange, String honoraryRange, String contactNumber) {
        this.name = name;
        this.instituteName = instituteName;
        this.subName = subName;
        this.year = year;
        this.classRange = classRange;
        this.honoraryRange = honoraryRange;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getClassRange() {
        return classRange;
    }

    public void setClassRange(String classRange) {
        this.classRange = classRange;
    }

    public String getHonoraryRange() {
        return honoraryRange;
    }

    public void setHonoraryRange(String honoraryRange) {
        this.honoraryRange = honoraryRange;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
