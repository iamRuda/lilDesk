package com.iamruda.lildesk.tables;

public class Status {
    private int idStatus;
    private String name;
    private String description;

    public Status(int idStatus, String name, String description) {
        this.idStatus = idStatus;
        this.name = name;
        this.description = description;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
