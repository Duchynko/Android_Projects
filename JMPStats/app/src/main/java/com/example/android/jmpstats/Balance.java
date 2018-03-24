package com.example.android.jmpstats;

/**
 * Created by jakub on 3/7/2018.
 */

public class Balance {

    private String balanceCrypto;
    private String pendingCrypto;

    public Balance(String balance, String pendingBalance){
        this.balanceCrypto = balance;
        this.pendingCrypto = pendingBalance;
    }

    public void setBalance(String balance, String pendingBalance){
        this.balanceCrypto = balance;
        this.pendingCrypto = pendingBalance;
    }

    public String getBalanceCrypto(){
        return balanceCrypto;
    }

    public void setBalanceCrypto(String balanceCrypto){
        this.balanceCrypto = balanceCrypto;
    }

    public String getPendingCrypto() {
        return pendingCrypto;
    }

    public void setPendingCrypto(String pendingCrypto) {
        this.pendingCrypto = pendingCrypto;
    }

}
