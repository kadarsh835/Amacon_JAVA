package com.version_1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.All_exceptions.*;

public class customer extends account implements Serializable {
    private double accountBalance;
    private double cartAmount;

    private Map<String,Integer> cart = new HashMap<>();

    public customer(String userId){
        super(userId);
        accountBalance = 0.0;
        cartAmount = 0.0;
    }

    public void addBalance(double money){
        accountBalance += money;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void addProduct(database database, String product, int quantity) throws ItemDoesNotExist,ItemOutOfStock{
        try {
            database.search(product);
        }
        catch (ItemDoesNotExist itemDoesNotExist) {
            itemDoesNotExist.printStackTrace();
        }
        if(quantity<=((product)database.search(product)).getQuantity()) {
            cart.put(product, quantity);
            cartAmount += cart.get(product) * ((product)database.search(product)).getPrice();
        }
        else
            throw new ItemOutOfStock();
    }

    public double checkOut(database database) throws InsufficientFunds{
        Set<Map.Entry<String,Integer>> setRef = cart.entrySet();
        for(Map.Entry<String,Integer> iterator : setRef){
            if(accountBalance >= cartAmount) {
                try {
                    accountBalance -= database.sale(iterator.getKey(), iterator.getValue(), accountBalance);
                } catch (ItemDoesNotExist | ItemOutOfStock | InsufficientFunds itemDoesNotExist) {
                    itemDoesNotExist.printStackTrace();
                }
            }
            else
                throw new InsufficientFunds();
        }
        cart = new HashMap<>();
        return cartAmount;
    }

    public void viewCart(){
        System.out.println("Your Cart:");
        Set<Map.Entry<String,Integer>> setRef = cart.entrySet();
        for(Map.Entry<String,Integer> iterator : setRef){
            System.out.println(iterator.getKey() + " : " + iterator.getValue());
        }
    }
}
