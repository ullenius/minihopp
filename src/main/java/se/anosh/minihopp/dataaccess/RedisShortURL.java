/**
 * 
 * Redis NoSQL implementation of the DAO-interface
 * 
 * Borde vara singleton? REdis är single-threaded? Nej den har lösningar för sånt
 * 
 */
package se.anosh.minihopp.dataaccess;

import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import se.anosh.minihopp.domain.ShortURL;
import se.anosh.minihopp.dataaccess.api.ShortURLDataAccess;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Alternative
@Stateless
public class RedisShortURL implements ShortURLDataAccess {
    

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(ShortURL newURL) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ShortURL findbyId(int id) throws ShortURLNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ShortURL findByName(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ShortURL> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
