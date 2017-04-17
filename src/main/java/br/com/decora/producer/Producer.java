package br.com.decora.producer;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Producer {

    @Produces
    public Datastore createMongoDBConnection(){
        final Morphia morphia = new Morphia();
        morphia.mapPackage("br.com.decora.entity");
        return morphia.createDatastore(new MongoClient("localhost", 27017), "userManager");
    }
}
