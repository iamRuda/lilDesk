package com.iamruda.lildesk.tables;

public class ContractWork {
    private int numberContractWork;
    private String dateConclusion;
    private String placeConclusion;
    private String placeWork;
    private int numberWorkBook;
    private String linkDoc;

    public ContractWork(int numberContractWork, String dateConclusion, String placeConclusion, String placeWork, int numberWorkBook, String linkDoc) {
        this.numberContractWork = numberContractWork;
        this.dateConclusion = dateConclusion;
        this.placeConclusion = placeConclusion;
        this.placeWork = placeWork;
        this.numberWorkBook = numberWorkBook;
        this.linkDoc = linkDoc;
    }

    public int getNumberContractWork() {
        return numberContractWork;
    }

    public String getDateConclusion() {
        return dateConclusion;
    }

    public String getPlaceConclusion() {
        return placeConclusion;
    }

    public String getPlaceWork() {
        return placeWork;
    }

    public int getNumberWorkBook() {
        return numberWorkBook;
    }

    public String getLinkDoc() {
        return linkDoc;
    }
}
