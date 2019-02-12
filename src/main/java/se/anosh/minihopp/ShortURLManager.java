/**
 * 
 * This is where the magic happens
 * creating ShortURL-objects etc...
 * 
 * 
 */
package se.anosh.minihopp;

import java.net.URL;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import se.anosh.minihopp.domain.ShortURL;
import se.anosh.minihopp.dataaccess.api.ShortURLDataAccess;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Default
@Stateless
public class ShortURLManager implements ShortURLService {

    @Inject
    ShortURLDataAccess dao;
    
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
    public List<ShortURL> listAllURLs() {
        return dao.findAll();
    }

    /**
     * 
     * Reverse name lookup. For where you have the long
     * URL (of an existing entry) but not the shorthand-version.
     * 
     * This is used mainly by the REST-service layer when
     * creating new entries and returning a JSON-object to
     * the client.

     * @param url
     * @return 
     */
    @Override
    public ShortURL findShortURLName(String url) {
        return dao.findByName(url);
    }
    
}
