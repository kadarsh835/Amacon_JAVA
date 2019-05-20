package com.version_2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.version_1.*;
import com.All_exceptions.*;

class userInterface implements Serializable {

    private database database_obj = new database();
    private account loggedInAs = null;
    private int login = 0;
    private List<account> accounts = new ArrayList<>();

    private void createAdminAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username:");
        String userName = sc.nextLine();
        admin admin = new admin(userName);
        accounts.add(admin);
        login = 1;
        loggedInAs = admin;
    }

    private void createCustomerAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username:");
        String userName = sc.nextLine();
        customer customer = new customer(userName);
        accounts.add(customer);
        login = 1;
        loggedInAs = customer;
    }

    private account searchAccount(String userName, int type) throws AccountDoesNotExist{
        for(account iterator: accounts){
            if(iterator instanceof admin && type == 1){
                if (iterator.getUserId().equals(userName)) {
                    login = 1;
                    return iterator;
                }
            }
            if(iterator instanceof customer && type == 2){
                if (iterator.getUserId().equals(userName)) {
                    login = 1;
                    return iterator;
                }
            }
        }
        throw new AccountDoesNotExist();
    }

    public void Interface(){
        Scanner sc = new Scanner(System.in);
        int choice, exit = 0;
        double revenueFromSession = 0.0;
        while (exit == 0){

            if(login == 0) {
                System.out.print("1.Login\n" +
                        "2.Signup\n" +
                        "3.Exit\n" +
                        "Enter Choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice == 3) {
                    exit = 1;
                    System.out.println("Revenue generated from session: " + revenueFromSession);
                    break;
                } else if (choice != 1 && choice != 2) {
                    System.out.println("Wrong Choice !!!");
                    continue;
                } else if (choice == 2) {
                    System.out.print("1. SignUp as an Admin\n" +
                            "2. SignUp as a Customer\n" +
                            "3.Exit\n" + "" +
                            "Enter Choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    if (choice == 3) {
                        exit = 1;
                        break;
                    } else if (choice != 1 && choice != 2) {
                        System.out.println("Wrong Choice !!!");
                        continue;
                    } else if (choice == 1)
                        createAdminAccount();
                    else
                        createCustomerAccount();
                } else {       //Login
                    System.out.print("1. LogIn as an Admin\n" +
                            "2. LogIn as a Customer\n" +
                            "3.Exit\n" +
                            "Enter Choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    if (choice == 3) {
                        exit = 1;
                        break;
                    } else if (choice != 1 && choice != 2) {
                        System.out.println("Wrong Choice !!!");
                        continue;
                    } else if (choice == 1) {
                        System.out.print("Enter UserName: ");
                        String userName = sc.nextLine();
                        try {
                            loggedInAs = searchAccount(userName, 1);
                        } catch (AccountDoesNotExist accountDoesNotExist) {
                            accountDoesNotExist.printStackTrace();
                        }
                    } else {
                        System.out.print("Enter UserName: ");
                        String userName = sc.nextLine();
                        try {
                            loggedInAs = searchAccount(userName, 2);
                        } catch (AccountDoesNotExist accountDoesNotExist) {
                            accountDoesNotExist.printStackTrace();
                        }
                    }
                }
            }

            if(loggedInAs instanceof admin) {
                System.out.print("1.Insert product/category\n" +
                        "2. Delete product/category\n" +
                        "3. Search product\n" +
                        "4. Modify product\n" +
                        "5. Exit as Administrator\n"+
                        "6. Exit\n" +
                        "Enter Choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.print("1. Insert Category\n" +
                                "2. Insert Product\n"+
                                "Enter Choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        switch (choice){
                            case 1:{
                                System.out.print("Enter Category Path:");
                                String categoryPath = sc.nextLine();
                                database_obj.insertCategory(categoryPath);
                            }break;
                            case 2:{
                                System.out.print("Enter Category Path:");
                                String categoryPath = sc.nextLine();
                                System.out.print("Enter Product Name:");
                                String productName = sc.nextLine();
                                try{
                                    database_obj.insertProduct(categoryPath,productName);
                                } catch (ItemAlreadyExits itemAlreadyExits) {
                                    itemAlreadyExits.printStackTrace();
                                }
                            }break;
                            default:System.out.println("Wrong Choice !!!");
                        }
                    }break;
                    case 2: {
                        System.out.print("Enter path: ");
                        String path = sc.nextLine();
                        try {
                            database_obj.delete(path);
                        } catch (ItemDoesNotExist itemDoesNotExist) {
                            itemDoesNotExist.printStackTrace();
                        }
                    }break;
                    case 3:{
                        System.out.print("Enter product: ");
                        String product = sc.nextLine();
                        try {
                            database_obj.search(product);
                        } catch (ItemDoesNotExist itemDoesNotExist) {
                            itemDoesNotExist.printStackTrace();
                        }
                    }break;
                    case 4:{
                        System.out.print("Enter product: ");
                        String product = sc.nextLine();
                        System.out.print("Enter new quantity: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        try {
                            database_obj.modify(product,quantity,price);
                        } catch (ItemDoesNotExist itemDoesNotExist) {
                            itemDoesNotExist.printStackTrace();
                        }
                    }break;
                    case 5:{
                        login = 0;
                        loggedInAs = null;
                    }break;
                    case 6:{
                        exit = 1;
                        login = 0;
                    }break;
                    default:System.out.println("Wrong Choice !!!");
                }
            }

            if(loggedInAs instanceof customer){
                System.out.print("1. Add funds\n" +
                        "2. Add product to the cart\n" +
                        "3. Check-out cart\n" +
                        "4. Exit as Customer\n" +
                        "5. Exit\n" +
                        "6. View Account Balance\n" +
                        "7. View Cart\n" +
                        "Enter Choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.print("Enter amount to be added: ");
                        double addAmount = sc.nextDouble();
                        sc.nextLine();
                        ((customer) loggedInAs).addBalance(addAmount);
                    }break;
                    case 2:{
                        while(true){
                            System.out.print("Enter Product to be added: ");
                            String product = sc.nextLine();
                            System.out.print("Enter Quantity to be added: ");
                            int quantity = sc.nextInt();
                            sc.nextLine();
                            try {
                                ((customer) loggedInAs).addProduct(database_obj,product,quantity);
                            } catch (ItemDoesNotExist | ItemOutOfStock itemDoesNotExist) {
                                itemDoesNotExist.printStackTrace();
                            }
                            System.out.print("Want to add more(0/1): ");
                            choice = sc.nextInt();
                            sc.nextLine();
                            if(choice == 0)
                                break;
                        }
                    }break;
                    case 3:{
                        try {
                            revenueFromSession += ((customer) loggedInAs).checkOut(database_obj);
                        } catch (InsufficientFunds insufficientFunds) {
                            insufficientFunds.printStackTrace();
                        }
                    }break;
                    case 4:{
                        login = 0;
                        loggedInAs = null;
                    }break;
                    case 5:{
                        exit = 1;
                        login = 0;
                    }break;
                    case 6:{
                        System.out.println("Account Balance: " + ((customer)loggedInAs).getAccountBalance());
                    }break;
                    case 7:{
                        ((customer)loggedInAs).viewCart();
                    }break;
                    default:System.out.println("Wrong Choice !!!");
                }
            }


        }
    }

}