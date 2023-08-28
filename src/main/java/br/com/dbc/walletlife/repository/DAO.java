package br.com.dbc.walletlife.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("dac");
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

}
