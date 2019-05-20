package com;

import java.io.*;
import com.All_exceptions.*;

public class serializerDeserializer{
    public int serialize(Object obj, String filename){
        try{

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(obj);
            out.close();
            file.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Object deserialize(String filename) throws FileDoesNotExists{
        Object obj = null;
        if(new File(filename).exists()){
            try{
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                obj = in.readObject();

                return obj;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            throw new FileDoesNotExists();
        return obj;
    }
}