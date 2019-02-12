package se.anosh.minihopp.dataaccess;

/**
 * 
 * SQL implementation of the DAO-interface
 * 
 */

import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.anosh.minihopp.domain.ShortURL;
import se.anosh.minihopp.dataaccess.api.ShortURLDataAccess;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Default
@Stateless
public class SQLShortURL implements ShortURLDataAccess {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void remove(int id) {
        em.remove(id);
    }

    @Override
    public void add(ShortURL url) {
        em.persist(url);
    }

    @Override
    public ShortURL findbyId(int id) throws ShortURLNotFoundException {
        
        ShortURL result = em.find(ShortURL.class,id);
        if (result == null) {
            throw new ShortURLNotFoundException("URL: " + id + " was not found in database");
        }
        return result;
    }
    
    @Override
    /**
     * 
     * TODO: Replace this with Optional<ShortURL>
     * or throw ShortURLNotFoundException
     */
    public ShortURL findByName(String url) {
        
        Query myQuery = em.createQuery("SELECT u FROM ShortURL u WHERE u.original LIKE :param");
        myQuery.setParameter("param", url);
        List<ShortURL> resultList = myQuery.getResultList();
        if (resultList.isEmpty())
            return null;
        return resultList.get(0);
    }


    @Override
    public List<ShortURL> findAll() {
        
        Query myQuery = em.createQuery("SELECT s FROM ShortURL s");
        return myQuery.getResultList();
    }

}
