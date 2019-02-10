/**
 * 
 * This is where the magic happens
 * 
 * random generation of strings (not in beta version)
 * creating ShortURL-objects etc...
 * 
 * 
 */
package se.anosh.minihopp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import se.anosh.minihopp.dataaccess.ShortURLNotFoundException;
import se.anosh.minihopp.dataaccess.URLdataAccess;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Default
@Stateless
public class ShortURLManager implements ShortURLService {

    @Inject
    URLdataAccess dao;
    
    @Override
    public void addURL(URL url) {
        ShortURL mini = new ShortURL(url);
        dao.add(mini);
    }

    @Override
    public void deleteURL(int id) {
        dao.remove(id);
    }

    @Override
    public ShortURL findURL(int id) throws ShortURLNotFoundException {
        
        return dao.findbyId(id);
    }

    @Override
    /**
     * 
     * This is a horrible mess.
     */
    public List<URL> listAllURLs() {
        
        List<ShortURL> allShortOnes = dao.findAll();
        List<String> allURLs = new ArrayList<>();
        
        for (ShortURL mini : allShortOnes) {
            allURLs.add(mini.getOriginal());
        }
       
        // create URL objects
       List<URL> realURLs = new ArrayList<>();
       
       for (String s : allURLs) {
            try {
                realURLs.add(new URL(s));
            } catch (MalformedURLException ex) {
               ex.printStackTrace();
            }
           
       }
       return realURLs;
       
       
    }

    /**
     * Returns the short-form URL based on the long URL
     * This call is used when creating new entries in the
     * database in order to return a JSON to the client
     * sending the POST-request
     * 
     * @param url
     * @return 
     */
    @Override
    public ShortURL findShortURLName(String url) {
        return dao.findByName(url);
    }
    
}
