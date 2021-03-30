package app.bd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("lp3");


    public EntityManager getEntityManager() {
        return factory.createEntityManager();

    }

    public void close() {
        factory.close();
    }
}

