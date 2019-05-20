package com.version_1;

import java.io.Serializable;

public abstract class account implements Serializable {
    private String userId;

    public account(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
