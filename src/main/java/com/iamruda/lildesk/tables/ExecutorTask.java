package com.iamruda.lildesk.tables;

public class ExecutorTask {
    private int idExecutorTask;
    private int idWorker;
    private int idTask;

    public ExecutorTask(int idExecutorTask, int idWorker, int idTask) {
        this.idExecutorTask = idExecutorTask;
        this.idWorker = idWorker;
        this.idTask = idTask;
    }

    public int getIdExecutorTask() {
        return idExecutorTask;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public int getIdTask() {
        return idTask;
    }
}
