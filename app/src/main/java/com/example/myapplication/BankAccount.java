package com.example.myapplication;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount){
        if (amount >0){
            balance += amount;
            System.out.println(amount + " TL has been deposited to your account. Current balance: "+ balance);
        }else{
            System.out.println("Invalid amount!");
        }
    }

    public void withdraw(int amount){
        if (amount <=0){
            System.out.println("Invalid amount!");
        }else if(amount > balance){
            System.out.println("Not enough balance! You have " + balance + " TL in your account.");
        }else{
            balance -= amount; //bakiyeden parayı düştük
            System.out.println(amount + " TL was withdrew from your account. Current balance: " + balance);

        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
