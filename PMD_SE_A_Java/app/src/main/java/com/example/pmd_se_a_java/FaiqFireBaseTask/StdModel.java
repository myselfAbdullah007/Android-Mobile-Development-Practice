package com.example.pmd_se_a_java.FaiqFireBaseTask;

import java.io.Serializable;
public class StdModel implements Serializable {
    private String name;
    private String section;
    private String rollNo;
    private String cnic;
    private String cgpa;

    public StdModel() {
    }

    public StdModel(String name, String section, String rollNo, String cnic, String cgpa) {
        this.name = name;
        this.section = section;
        this.rollNo = rollNo;
        this.cnic = cnic;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getCnic() {
        return cnic;
    }

    public String getCgpa() {
        return cgpa;
    }
}
