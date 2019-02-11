package se.anosh.minihopp.dataaccess;

import java.util.List;
import javax.ejb.Local;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Local
public interface URLDataAccess {
    
    public void remove(int id);
    public void add(ShortURL newURL);
    public ShortURL findbyId(int id) throws ShortURLNotFoundException;
    public ShortURL findByName(String url);
    public List<ShortURL> findAll();
}
