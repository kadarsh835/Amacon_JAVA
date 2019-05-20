package com.version_1;

import static org.junit.jupiter.api.Assertions.*;
import com.All_exceptions.*;

class databaseTest {

    @org.junit.jupiter.api.Test
    void insertProduct() throws ItemAlreadyExits {
        database testDatabase = new database();
        testDatabase.insertProduct("Electronics > Mobiles > Smartphones","Oppo OnePlus");
        assertThrows(ItemAlreadyExits.class,()->{
            testDatabase.insertProduct("Electronics > Mobiles > Smartphones","Oppo OnePlus");
        });
    }

    @org.junit.jupiter.api.Test
    void search() {
        database testDatabase = new database();
        assertThrows(ItemDoesNotExist.class,()->{
            testDatabase.search("Electronics > Mobiles > Smartphones > Oppo OnePlus");});
    }

    @org.junit.jupiter.api.Test
    void delete() {
        database testDatabase = new database();
        assertThrows(ItemDoesNotExist.class,()->{
            testDatabase.delete("Electronics > Mobiles > Smartphones > Oppo OnePlus");
        });
    }
}