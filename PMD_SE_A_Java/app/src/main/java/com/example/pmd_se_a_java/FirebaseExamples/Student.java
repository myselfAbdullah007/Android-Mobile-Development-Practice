package com.example.pmd_se_a_java.FirebaseExamples;

public class Student {
    String Name,url;

    public Student() {

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return url;
    }

    public Student(String name, String url) {
        Name = name;
        this.url = url;
    }
}
