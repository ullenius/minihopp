package se.anosh.minihopp.dataaccess;

/**
 * 
 * SQL implementation of the DAO-interface
 * 
 */

import java.net.URL;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Stateless
public class URLdataAccessImplementation implements URLdataAccess {

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
    public ShortURL findbyId(int id) {
        
        return em.find(ShortURL.class, id);
    }
    
    @Override
    /**
     * 
     * TODO: Replace this with Optional<ShortURL>
     * or throw ShortURLNotFoundException
     */
    public ShortURL findByName(URL url) {
        
        Query myQuery = em.createQuery("SELECT u FROM ShortURL u WHERE u.original LIKE :param");
        myQuery.setParameter("param", url.toString());
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
