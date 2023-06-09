package com.iamruda.lildesk.tables;

public class Client {
    private int idClient;
    private String fullName;
    private String review;
    private String contactPhone;
    private String email;

    public Client(int idClient, String fullName, String review, String contactPhone, String email) {
        this.idClient = idClient;
        this.fullName = fullName;
        this.review = review;
        this.contactPhone = contactPhone;
        this.email = email;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getFullName() {
        return fullName;
    }

    public String getReview() {
        return review;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getEmail() {
        return email;
    }
}
