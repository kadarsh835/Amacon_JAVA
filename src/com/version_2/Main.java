package com.version_2;

import com.All_exceptions.*;
import com.serializerDeserializer;

import java.io.File;

public class Main {
    public static void main(String[] args){

        userInterface userInterface = null;
        serializerDeserializer serializeDeserialize = new serializerDeserializer();
        int returnValue = 0;
        String filename = "Account_details";
        if(new File(filename).exists()) {
            try {
                userInterface = (com.version_2.userInterface) serializeDeserialize.deserialize(filename);
            } catch (FileDoesNotExists fileDoesNotExists) {
                fileDoesNotExists.printStackTrace();
            }
        }
        else
            userInterface = new userInterface();

        assert userInterface != null;
        userInterface.Interface();

        returnValue = serializeDeserialize.serialize(userInterface, filename);
    }
}