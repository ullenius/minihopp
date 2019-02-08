package se.anosh.minihopp.dataaccess;

/**
 * 
 * SQL implementation of the DAO-interface
 * 
 */

import java.net.URL;
import java.util.List;
import java.net.URL;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public class URLdataAccessImplementation implements URLdataAccess {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void remove(int id) {
        
        
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(URL newUrl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public URL findbyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public URL findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<URL> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
