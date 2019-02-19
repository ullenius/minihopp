package se.anosh.minihopp.dataaccess.api;

import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Local
public interface ShortURLDataAccess {
    
    public void remove(int id);
    public Optional<Integer> add(ShortURL newURL);
    public ShortURL findbyId(int id) throws ShortURLNotFoundException;
    public ShortURL findByName(String url) throws ShortURLNotFoundException;
    public List<ShortURL> findAll();
}
