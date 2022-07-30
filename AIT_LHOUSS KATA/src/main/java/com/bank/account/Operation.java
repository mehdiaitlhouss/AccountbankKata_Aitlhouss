package com.bank.account;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Operation {

    private final String key;

    private final LocalDateTime date;

    private final Double amount;

    public Operation(String key, LocalDateTime date, Double amount) {
        this.key = key;
        this.date = date;
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation operation = (Operation) o;
        return Objects.equals(getKey(), operation.getKey()) &&
                Objects.equals(getDate(), operation.getDate()) &&
                Objects.equals(getAmount(), operation.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getDate(), getAmount());
    }
}
