package com.iamruda.lildesk.tables;

public class ContractDev {
    private int numberContractDev;
    private int idClient;
    private String itemContract;
    private String priceContract;
    private String linkDoc;

    public ContractDev(int numberContractDev, int idClient, String itemContract, String priceContract, String linkDoc) {
        this.numberContractDev = numberContractDev;
        this.idClient = idClient;
        this.itemContract = itemContract;
        this.priceContract = priceContract;
        this.linkDoc = linkDoc;
    }

    public int getNumberContractDev() {
        return numberContractDev;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getItemContract() {
        return itemContract;
    }

    public String getPriceContract() {
        return priceContract;
    }

    public String getLinkDoc() {
        return linkDoc;
    }
}
