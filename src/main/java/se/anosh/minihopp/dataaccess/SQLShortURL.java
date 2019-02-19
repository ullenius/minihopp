package se.anosh.minihopp.dataaccess;

/**
 * 
 * SQL implementation of the DAO-interface
 * 
 */

import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
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
    public Optional<Integer> add(ShortURL url) {
        em.persist(url);
        return Optional.empty(); // not supported by relational databases
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
    public ShortURL findByName(String url) throws ShortURLNotFoundException {
        
        Query myQuery = em.createQuery("SELECT u FROM ShortURL u WHERE u.longFormatURL LIKE :param");
        myQuery.setParameter("param", url);
        List<ShortURL> resultList = myQuery.getResultList();
        if (resultList.isEmpty())
            throw new ShortURLNotFoundException("URL with name: " + url + " was not found in database");
        return resultList.get(0);
    }

    @Override
    public List<ShortURL> findAll() {
        
        Query myQuery = em.createQuery("SELECT s FROM ShortURL s");
        return myQuery.getResultList();
    }

}
