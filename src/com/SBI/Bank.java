package com.SBI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
    private List<Account> accountList= new ArrayList<>();
    protected List<Transaction> transactionList = new ArrayList<>();

    Bank(){
        for(int i=0; i<10000; i++){
            Account a = new Account(i,1000);
            accountList.add(a);
        }

        Random rand = new Random();
        for(int i=0; i<10000000; i++){
            int num1 = rand.nextInt(10000);
            int num2 = rand.nextInt(10000);
            Transaction t = new Transaction(num1, num2, 5);
            transactionList.add(t);
        }
    }

    public int getTotalBalance(){
        int sum=0;
        for(Account a : accountList){
            sum = sum + a.getBalance();
        }
        return sum;
    }

    public synchronized void process(int account1, int account2){
        if(accountList.get(account1).getBalance()>=5){
            accountList.get(account1).setBalance(accountList.get(account1).getBalance()-5);
            accountList.get(account2).setBalance(accountList.get(account2).getBalance()+5);
            // System.out.println("Success");
        }else{
            // System.out.println("Fail");
        }
    }
}


class Account{
    private int id, balance;

    Account(int id, int balance){
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int newBalance){
        balance = newBalance;
    }
}


class Transaction{
    private int account1;
    private int account2;
    private int amount;
    Transaction(int account1, int account2, int amount){
        this.account1 = account1;
        this.account2 = account2;
        this.amount = amount;
    }

    public int getAccount1() {
        return account1;
    }

    public int getAccount2() {
        return account2;
    }
}