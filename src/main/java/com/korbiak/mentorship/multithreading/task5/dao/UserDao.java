package com.korbiak.mentorship.multithreading.task5.dao;

import com.korbiak.mentorship.multithreading.task5.model.UserAccount;

public interface UserDao {

    void saveUser(UserAccount account);

    UserAccount getUser(String name, String secondaryName);
}
