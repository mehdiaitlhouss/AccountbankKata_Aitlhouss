package com.bank.account;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class OperationTest {

    private Account account;

    @Test
    public void doNoThing(){

    }

    @Test
    public void depositTest(){
        final double BANANCE = 100;
        account = Account.from(new ArrayList<>(),BANANCE);
        final Double MONEY_TO_DESPOSIT = 100.0;
        OperationDeposit operation = OperationDeposit.from("OP1", LocalDateTime.now(),MONEY_TO_DESPOSIT);
        Account newaccount = account.deposit(operation);
        Double newBalance = account.getBalance()+MONEY_TO_DESPOSIT;
        assertEquals(newaccount.getBalance(),newBalance);
        assertEquals(newaccount.getOperations().size(),account.getOperations().size()+1);
    }

    @Test
    public void withdrawalIfTherIsEnoughMoney(){
        final double BANANCE = 100;
        account = Account.from(new ArrayList<>(),BANANCE);
        final Double MONEY_TO_WITHDRAWAL = 70.0;
        OperationWithdrawal operation = OperationWithdrawal.from("OP1", LocalDateTime.now(),MONEY_TO_WITHDRAWAL);
        Optional<Account> newaccount = account.withdrawal(operation);
        Double newBalance = account.getBalance() - MONEY_TO_WITHDRAWAL;
        assertEquals(newaccount.orElse(account).getBalance(),newBalance);
        assertEquals(newaccount.orElse(account).getOperations().size(),account.getOperations().size()+1);
    }

    @Test
    public void withdrawalIfTherIsNotEnoughMoney() {
        final double BANANCE = 100;
        account = Account.from(new ArrayList<>(),BANANCE);
        final Double MONEY_TO_WITHDRAWAL = 200.0;
        OperationWithdrawal operation = OperationWithdrawal.from("OP1", LocalDateTime.now(), MONEY_TO_WITHDRAWAL);
        Optional<Account> newaccount = account.withdrawal(operation);
        assertEquals(newaccount.orElse(account).getBalance(), account.getBalance());
        assertEquals(newaccount.orElse(account).getOperations().size(), account.getOperations().size());
    }

    @Test
    public void historyOfOperations(){
        final double BANANCE = 100;
        account = Account.from(new ArrayList<>(),BANANCE);
        final Double MONEY_TO_DESPOSIT = 100.0;

        OperationDeposit operation1 = OperationDeposit.from("OP1", LocalDateTime.now(),MONEY_TO_DESPOSIT);
        Account newaccount = account.deposit(operation1);

        OperationDeposit operation2 = OperationDeposit.from("OP2", LocalDateTime.now(),MONEY_TO_DESPOSIT);
        newaccount = newaccount.deposit(operation2);

        final Double MONEY_TO_WITHDRAWAL = 200.0;

        OperationWithdrawal operation3 = OperationWithdrawal.from("OP3", LocalDateTime.now(),MONEY_TO_WITHDRAWAL);
        newaccount = newaccount.withdrawal(operation3).orElse(newaccount);

        OperationWithdrawal operation4 = OperationWithdrawal.from("OP4", LocalDateTime.now(),MONEY_TO_WITHDRAWAL);
        newaccount = newaccount.withdrawal(operation4).orElse(newaccount);

        assertEquals(newaccount.getOperations().size(),3);
        assertEquals(newaccount.getBalance(),Double.valueOf(100.0));
        assertEquals(newaccount.getOperations().get(0),operation1);
        assertEquals(newaccount.getOperations().get(1),operation2);
        assertEquals(newaccount.getOperations().get(2),operation3);
    }

}