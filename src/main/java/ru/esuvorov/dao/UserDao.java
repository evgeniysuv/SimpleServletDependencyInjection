package ru.esuvorov.dao;

import ru.esuvorov.model.User;

/**
 * Created by esuvorov on 5/7/17.
 */
public interface UserDao {
    String getName();

    void createUser(User user);
}
