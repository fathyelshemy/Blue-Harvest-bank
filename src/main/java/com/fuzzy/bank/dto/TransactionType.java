package com.fuzzy.bank.dto;

public enum TransactionType {
    WITHDRAWAL("withdrawal"),
    DEPOSIT("deposit");
    private String value;

    String getValue() {
        return value;
    }

    TransactionType(String value) {
        this.value = value;
    }
}
