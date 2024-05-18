package com.example.pmd_se_a_java.ClassTask7;

public class product {
    private String id;
    private String name;
    private String company;
    private String price;
    private String imageUrl;

    public product() {
    }

    public product(String id, String name, String company, String price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
