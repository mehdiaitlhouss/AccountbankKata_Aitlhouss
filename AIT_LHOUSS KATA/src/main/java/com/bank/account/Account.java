package com.bank.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Account {

    private final List<Operation> operations;

    private final Double balance;

    private Account(List<Operation> operations, Double balance) {
        this.operations = operations;
        this.balance = balance;
    }

    public static Account from(List<Operation> operations, Double balance) {
        return new Account(operations,balance);
    }

    public Account deposit(OperationDeposit operation) {
        List<Operation> operations = addOperation(operation);
        return Account.from(operations,balance+operation.getAmount());
    }

    public Optional<Account> withdrawal(OperationWithdrawal operation){
        if(!thereIsEnoughMoney(operation.getAmount())) return Optional.empty();
        List<Operation> operations = addOperation(operation);
        return Optional.of(Account.from(operations,balance-operation.getAmount()));
    }

    private boolean thereIsEnoughMoney(Double amount){
        if(balance<amount) return false;
        return true;
    }

    private List<Operation> addOperation(Operation operation){
        List<Operation> operations = (List<Operation>) ((ArrayList)this.operations).clone();
        operations.add(OperationDeposit.from(operation.getKey(),operation.getDate(),operation.getAmount()));
        return operations;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Double getBalance() {
        return balance;
    }
}
