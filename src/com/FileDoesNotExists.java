package com;

public class FileDoesNotExists extends Exception{
    FileDoesNotExists(){
        super("The Object Does Not Exists");
    }
}