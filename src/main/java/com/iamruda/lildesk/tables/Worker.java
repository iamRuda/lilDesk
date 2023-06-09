package com.iamruda.lildesk.tables;

public class Worker {
    private int idWorker;
    private String fullName;
    private int numberContractWork;
    private String post;
    private String skills;
    private int salary;

    public Worker(int idWorker, String fullName, int numberContractWork, String post, String skills, int salary) {
        this.idWorker = idWorker;
        this.fullName = fullName;
        this.numberContractWork = numberContractWork;
        this.post = post;
        this.skills = skills;
        this.salary = salary;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public String getFullName() {
        return fullName;
    }

    public int getNumberContractWork() {
        return numberContractWork;
    }

    public String getPost() {
        return post;
    }

    public String getSkills() {
        return skills;
    }

    public int getSalary() {
        return salary;
    }
}
