package com.version_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.All_exceptions.*;

public class database implements Serializable {

    private List<item> items = new ArrayList<>();

    private void helperInsertCategory(String category_path){
        category category_obj = new category(category_path);
        int flag = 0;
        for (item iterator:items){
            if(iterator instanceof category && iterator.getName().equals(category_path))
                flag = 1;
        }
        if(flag != 1)
            items.add(category_obj);
    }

    public void insertCategory(String category_path){

        String[] superCategories = category_path.split("( )*[>]( )*");
        String category = superCategories[0];
        helperInsertCategory(category);
        for(int i=1;i<superCategories.length;i++) {
            category += " > " + superCategories[i];
            helperInsertCategory(category);
        }
    }

    public void insertProduct(String category_path, String product) throws ItemAlreadyExits {

        String[] superCategories = category_path.split("( )*[>]( )*");
        String category = superCategories[0];
        insertCategory(category);
        for(int i=1;i<superCategories.length;i++){  //to insert all sublevels of the category
                category += " > " + superCategories[i];
                insertCategory(category);
        }
        product product_obj = new product(product);
        product_obj.setCategory(category);          //used category instead of category_path so that all the categories follow the same pattern

        for (item iterator:items){
            if(iterator instanceof product){
                if(iterator.getName().equals(product) && ((product) iterator).getCategory().equals(category) )
                    throw new ItemAlreadyExits();
            }
        }

        items.add(product_obj);
    }

    public item search(String product) throws ItemDoesNotExist {
        for (item iterator:items) {
            if (iterator instanceof product && iterator.getName().equals(product)) {
                System.out.println("Path: " + ((product) iterator).getCategory() + " > " + product);
                System.out.println("Details:\nQuantity in stock: " + ((product) iterator).getQuantity() + "\nPrice per item: " + ((product) iterator).getPrice());
                return iterator;
            }
        }
        throw new ItemDoesNotExist();
    }

    public void delete(String item_) throws ItemDoesNotExist{
        String[] superCategories = item_.split("( )*[>]( )*");
        int delete = 0;
        for (item iterator:items){
            String[] item_categories;
            if(iterator instanceof category)
                item_categories = (iterator.getName()).split("( )*[>]( )*");
            else
                item_categories = ((product)iterator).getPath().split("( )*[>]( )*");
            if(item_categories.length>=superCategories.length){
                int flag = 1;
                for(int i = 0; i<superCategories.length; i++){
                    if( !(item_categories[i].equals(superCategories[i])) ){
                        flag = 0;
                        break;
                    }
                }
                if(flag == 1){
                    System.out.println("Deleting " + iterator.getName());
                    items.remove(iterator);
                    delete = 1;
                    delete(item_);
                    break;
                }
            }
        }
        if(delete != 1)
            throw new ItemDoesNotExist();
    }

    public void modify(String product, int quantity, double price) throws ItemDoesNotExist {
        try{
            item product_= search(product);
            ((product)product_).setQuantity(quantity);
            ((product)product_).setPrice(price);
            System.out.println("Updated:");
            search(product);
        } catch (ItemDoesNotExist itemDoesNotExist) {
            itemDoesNotExist.printStackTrace();
        }
    }

    public double sale(String product, int quantity, double remainingFundsWithCustomer) throws InsufficientFunds, ItemOutOfStock, ItemDoesNotExist {
        try {
            item product_ = search(product);
            if( ((product)product_).getQuantity()<quantity)
                throw new ItemOutOfStock();
            else if((quantity*((product)product_).getPrice())>remainingFundsWithCustomer)
                throw new InsufficientFunds();
            else
                modify(product,((product)product_).getQuantity()-quantity,((product)product_).getPrice());
                return quantity*((product)product_).getPrice();
        } catch (ItemOutOfStock itemDoesNotExist) {
            itemDoesNotExist.printStackTrace();
        }
        return 0;
    }

    public void printItems(){
        for (item iterator : items) {
            if (iterator instanceof product) {
                System.out.println("Category: " + ((product) iterator).getCategory() + '\t' + "Product: " + iterator.getName());
            } else
                System.out.println(iterator.getName());
        }
    }
}

