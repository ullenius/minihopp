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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import se.anosh.minihopp.dataaccess.URLdataAccess;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public class ShortURLManager implements ShortURLService {

    @Inject
    URLdataAccess dao;
    
    @Override
    public void addURL(URL url) {
        
        ShortURL mini = new ShortURL();
        mini.setOriginal(url);
        mini.setContent(getRandomString());
        
        dao.add(mini);
    }

    @Override
    public void deleteURL(int id) {
        dao.remove(id);
    }

    @Override
    public URL findURL(int id) {
        
        ShortURL result = dao.findbyId(id);
        return result.getOriginal();
    }

    @Override
    public List<URL> listAllURLs() {
        
        List<ShortURL> allShortOnes = dao.findAll();
        List<URL> allURLs = new ArrayList<>();
        
        for (ShortURL mini : allShortOnes) {
            allURLs.add(mini.getOriginal());
        }
        
        return allURLs;
    }
    
    private String getRandomString() {
        
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
        
    }
    
}
