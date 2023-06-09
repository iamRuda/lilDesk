package com.iamruda.lildesk.tables;

public class Task {
    private int idTask;
    private String name;
    private int idProject;
    private String description;
    private String criterion;
    private String leadTime;
    private String opening;
    private String deadline;
    private String label;
    private int idStatus;

    public Task(int idTask, String name, int idProject, String description, String criterion, String leadTime, String opening, String deadline, String label, int idStatus) {
        this.idTask = idTask;
        this.name = name;
        this.idProject = idProject;
        this.description = description;
        this.criterion = criterion;
        this.leadTime = leadTime;
        this.opening = opening;
        this.deadline = deadline;
        this.label = label;
        this.idStatus = idStatus;
    }

    public int getIdTask() {
        return idTask;
    }

    public String getName() {
        return name;
    }

    public int getIdProject() {
        return idProject;
    }

    public String getDescription() {
        return description;
    }

    public String getCriterion() {
        return criterion;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public String getOpening() {
        return opening;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getLabel() {
        return label;
    }

    public int getIdStatus() {
        return idStatus;
    }
}