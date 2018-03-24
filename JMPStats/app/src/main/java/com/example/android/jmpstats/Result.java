package com.example.android.jmpstats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 3/14/2018.
 */

public class Result {
    private Balance balance;
    private double[] rates;
    private List<Transaction> transactions;

    public Result(Balance balance, double[] rates, List<Transaction> transactions){
        this.balance = balance;
        this.rates = rates;
        this.transactions = transactions;
    }

    public Balance getBalance() {
        return balance;
    }

    public double[] getRates() {
        return rates;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
