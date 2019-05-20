package com.All_exceptions;

public class AccountDoesNotExist extends Exception{
    public AccountDoesNotExist(){
        super("No account with the given userId exists !!!");
    }
}
