package com.iamruda.lildesk.tables;

public class ExecutorProject {
    private int idExecutorProject;
    private int idWorker;
    private int idProject;

    public ExecutorProject(int idExecutorProject, int idWorker, int idProject) {
        this.idExecutorProject = idExecutorProject;
        this.idWorker = idWorker;
        this.idProject = idProject;
    }

    public int getIdExecutorProject() {
        return idExecutorProject;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public int getIdProject() {
        return idProject;
    }
}
