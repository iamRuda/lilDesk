package com.iamruda.lildesk.tables;

public class Project {
    private int idProject;
    private String name;
    private int numberContractDev;
    private int numberTechDoc;
    private String deadline;

    public Project(int idProject, String name, int numberContractDev, int numberTechDoc, String deadline) {
        this.idProject = idProject;
        this.name = name;
        this.numberContractDev = numberContractDev;
        this.numberTechDoc = numberTechDoc;
        this.deadline = deadline;
    }

    public int getIdProject() {
        return idProject;
    }

    public String getName() {
        return name;
    }

    public int getNumberContractDev() {
        return numberContractDev;
    }

    public int getNumberTechDoc() {
        return numberTechDoc;
    }

    public String getDeadline() {
        return deadline;
    }
}
