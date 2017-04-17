package br.com.decora.dao;

import br.com.decora.entity.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import javax.inject.Inject;
import java.util.List;

public class UserDao implements Dao {

    @Inject
    private Datastore userDataStore;

    public void persist(User user) {
        userDataStore.save(user);
    }

    public List<User> findAll() {
        Query<User> query = userDataStore.createQuery(User.class);
        return query.asList();
    }
}
