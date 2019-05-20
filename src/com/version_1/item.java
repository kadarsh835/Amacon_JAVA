package com.version_1;

import java.io.Serializable;

abstract class item implements Serializable {
    private String name;
    public item(String name){
        this.name =name;
    }
    public String getName(){
        return this.name;
    }
}