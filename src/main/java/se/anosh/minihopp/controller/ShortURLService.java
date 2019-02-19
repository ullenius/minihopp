package se.anosh.minihopp.controller;

import java.net.MalformedURLException;
import java.util.List;
import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public interface ShortURLService {
    
    public void addURL(String url) throws MalformedURLException;
    public void deleteURL(int id);
    public ShortURL findURL(int id) throws ShortURLNotFoundException;
    public List<ShortURL> listAllURLs(); // debug method?
    public ShortURL findShortURLName(String url) throws ShortURLNotFoundException;
}
