package br.com.decora.dao;


import br.com.decora.entity.User;

import java.util.List;

public interface Dao {

    void persist(User user);
    List<User> findAll();
}
