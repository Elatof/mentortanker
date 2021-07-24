package com.korbiak.mentorship.multithreading.task5.dao;

import com.korbiak.mentorship.multithreading.task5.model.UserAccount;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class UserDaoImpl implements UserDao {

    private static final String STORAGE_PATH = "src/main/resources/task6/usersStorage/";

    public void saveUser(UserAccount account) {
        String fullName = account.getName() + "_" + account.getSecondaryName();
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(STORAGE_PATH + fullName))) {
            objectOutputStream.writeObject(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserAccount getUser(String name, String secondaryName) {
        log.info("Getting user {} {} from storage by path: {} | {}", name, secondaryName, STORAGE_PATH, Thread.currentThread());
        String fullName = name + "_" + secondaryName;
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(STORAGE_PATH + fullName))) {
            return (UserAccount) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.warn("User with name = {}, secondaryName = {}, not found | {}", name, secondaryName, Thread.currentThread());
        }
        return null;
    }
}
