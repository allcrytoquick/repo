package com.coinhub.DataModelPackage;

/**
 * Created by "MD.Ibrahim Khalil" on 28-Mar-18.
 */

/**
 * Transaction Model (POJO Data Model)
 */

public class TransactionModel {
    private int type;
    private String amount;
    private String total;
    private String cardNo;

    public TransactionModel(int type, String amount, String total, String cardNo) {
        this.type = type;
        this.amount = amount;
        this.total = total;
        this.cardNo = cardNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
