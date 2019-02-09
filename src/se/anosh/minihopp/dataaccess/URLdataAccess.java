package se.anosh.minihopp.dataaccess;

import java.net.URL;
import java.util.List;
import javax.ejb.Local;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Local
public interface URLdataAccess {
    
    public void remove(int id);
    public void add(ShortURL newURL);
    public ShortURL findbyId(int id);
    public ShortURL findByName(URL url);
    public List<ShortURL> findAll();
}
