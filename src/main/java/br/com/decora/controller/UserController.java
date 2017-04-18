package br.com.decora.controller;

import br.com.decora.dao.UserDao;
import br.com.decora.entity.User;

import javax.inject.Inject;
import java.util.List;


public class UserController {

    @Inject
    private UserDao userDao;

    public void persistUser(User user){
        userDao.persist(user);
    }

    public List<User> findAllUsers(){
        return userDao.findAll();
    }
}
