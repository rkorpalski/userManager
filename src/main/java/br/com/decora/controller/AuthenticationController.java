package br.com.decora.controller;

import br.com.decora.dao.UserDao;
import br.com.decora.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AuthenticationController implements Serializable {

    private final List<String> authorizationTokenStorage = new ArrayList<>();

    @Inject
    private UserDao userDao;

    public String login(String username, String password) throws LoginException{
        User user  = userDao.find("userName", username);
        if(validateUser(user, password)){
            String authToken = UUID.randomUUID().toString();
            authorizationTokenStorage.add(authToken);
            return authToken;
        }
        throw new LoginException("Authentication failed");
    }

    private Boolean validateUser(User user, String password){
        if(user != null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public Boolean isAuthorizationTokenValid(String authToken){
        if(authorizationTokenStorage.contains(authToken)){
          return true;
        }
        return false;
    }

    public void logout(String authToken){
        authorizationTokenStorage.remove(authToken);
    }

}
