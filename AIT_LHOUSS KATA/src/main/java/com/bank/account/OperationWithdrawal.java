package com.bank.account;

import java.time.LocalDateTime;

public class OperationWithdrawal extends Operation{

    private OperationWithdrawal(String key, LocalDateTime date, Double amount) {
        super(key, date, amount);
    }

    public static OperationWithdrawal from(String key, LocalDateTime date, Double amount){
        return new OperationWithdrawal(key,date,amount);
    }
}
