package se.anosh.minihopp;

import java.net.URL;
import java.util.List;
import javax.ejb.Remote;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Remote
public interface ShortURLService {
    
    void addURL(URL url); // we want to give back the user a JSON
    public void deleteURL(int id);
    public ShortURL findURL(int id); // in the prototype-versionen
    public List<URL> listAllURLs(); // debug method?
}
