package br.com.decora.ejb;

import br.com.decora.entity.User;
import org.mongodb.morphia.Datastore;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
public class SetupDB {

    private static final Logger logger = Logger.getLogger(SetupDB.class.getName());
    @Inject
    private Datastore userDatastore;

    @PostConstruct
    public void initDB(){
        try {
            userDatastore.delete(userDatastore.createQuery(User.class));

            userDatastore.save(new User("admin", "admin1234", "admin"));
            userDatastore.save(new User("user", "user1234", "user"));
        }catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
