package com.version_1;

import com.serializerDeserializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        userInterface userInterface = new userInterface();
        serializerDeserializer serializeDeserialize = new serializerDeserializer();
        assertEquals(1,serializeDeserialize.serialize(userInterface,"Account_details"));
    }
}