package com.korbiak.mentorship.io.task1;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Serializer {

    public void serializeObject(Parent object, String path) {
        try(ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(path))) {
            objectOutputStream.writeObject(object);
        } catch (FileNotFoundException e) {
            log.error("File not found by path: {}", path);
        } catch (IOException e) {
            log.error("Error serialization object: {}", e.getMessage());
        }
        log.info("Object was successfully serialized by path: {}", path);
    }

    public <T> T deserializeObject(String path, Class<T> targetClass) {
        try(ObjectInputStream objectOutputStream =
                    new ObjectInputStream(new FileInputStream(path))) {
            Object readObject = objectOutputStream.readObject();
            log.info("Object was successfully deserialized by path: {}", path);
            return targetClass.cast(readObject);
        } catch (IOException e) {
            log.error("Error serialization object: {}", e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException: {}", e.getMessage());
        }
        return null;
    }
}
