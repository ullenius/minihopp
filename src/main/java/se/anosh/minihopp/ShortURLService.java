package se.anosh.minihopp;

import java.net.URL;
import java.util.List;
import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public interface ShortURLService {
    
    public void addURL(URL url);
    public void deleteURL(int id);
    public ShortURL findURL(int id) throws ShortURLNotFoundException;
    public List<ShortURL> listAllURLs(); // debug method?
    public ShortURL findShortURLName(String url);
}
