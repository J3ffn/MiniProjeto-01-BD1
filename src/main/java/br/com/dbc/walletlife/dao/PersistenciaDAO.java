package br.com.dbc.walletlife.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class PersistenciaDAO {
    
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("miniProjetoBD1");
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
