package com.iamruda.lildesk.tables;

public class TechDoc {
    private int numberTechDoc;
    private String linkDoc;

    public TechDoc(int numberTechDoc, String linkDoc) {
        this.numberTechDoc = numberTechDoc;
        this.linkDoc = linkDoc;
    }

    public int getNumberTechDoc() {
        return numberTechDoc;
    }

    public String getLinkDoc() {
        return linkDoc;
    }
}
