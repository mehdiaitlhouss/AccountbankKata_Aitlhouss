package com.bank.account;


import java.time.LocalDateTime;

public class OperationDeposit extends Operation {

    private OperationDeposit(String key, LocalDateTime date, Double amount) {
        super(key, date, amount);
    }

    public static OperationDeposit from(String key, LocalDateTime date, Double amount){
        return new OperationDeposit(key,date,amount);
    }
}
