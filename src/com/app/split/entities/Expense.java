package com.app.split.entities;

public class Expense {
    String owedByUserId;
    String oweToUserId;
    String expenseType;
    Double amount;

    public Expense(String owedByUserId, String oweToUserId, String expenseType, Double amount) {
        this.owedByUserId = owedByUserId;
        this.oweToUserId = oweToUserId;
        this.expenseType = expenseType;
        this.amount = amount;
    }
}
