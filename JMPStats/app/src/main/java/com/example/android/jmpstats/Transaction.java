package com.example.android.jmpstats;

/**
 * Created by jakub on 3/18/2018.
 */

public class Transaction {
    private String date;
    private String payment;
    private String fee;

    public Transaction(String date, String payment, String fee){
        this.date = date;
        this.payment = payment;
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
