package com.version_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.All_exceptions.*;

class customerTest {

    @Test
    void addProduct() throws ItemAlreadyExits, ItemDoesNotExist {
        database testDatabase = new database();
        customer testCustomer = new customer("Adarsh");
        testDatabase.insertProduct("Electronics > Mobiles > Smartphones","Oppo OnePlus");
        testDatabase.modify("Oppo OnePlus",10,1000.0);
        assertThrows(ItemOutOfStock.class,()->{
            testCustomer.addProduct(testDatabase,"Oppo OnePlus",15);
        });
    }

    @Test
    void checkOut() throws InsufficientFunds, ItemAlreadyExits, ItemDoesNotExist, ItemOutOfStock {
        database testDatabase = new database();
        customer testCustomer = new customer("Adarsh");
        testDatabase.insertProduct("Electronics > Mobiles > Smartphones","Oppo OnePlus");
        testDatabase.modify("Oppo OnePlus",10,1000.0);
        testCustomer.addProduct(testDatabase,"Oppo OnePlus",1);
        assertThrows(InsufficientFunds.class,()->{
            testCustomer.checkOut(testDatabase);
        });
    }
}