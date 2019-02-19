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
import java.util.Objects;
import javax.ejb.Stateless;
import redis.clients.jedis.Jedis;
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
    
    private final String hostname;
    private int defaultExpiryTimeInSeconds;
    private int databaseId;
    
    public RedisShortURL() {
        hostname = "localhost";
        databaseId = 0; // default
    }
    
    public RedisShortURL(String hostname, int databaseId) {
        this.hostname = Objects.requireNonNull(hostname, "Hostname cannot be set to null");
        this.databaseId = databaseId;
    }
    
    private void switchDb(Jedis jedis) {
        jedis.select(databaseId);
    }

    @Override
    public void remove(int id) {
        
        try (Jedis jedis = new Jedis(hostname)) {
            switchDb(jedis);
            String key = String.valueOf(id);
            jedis.del(key);
        }
    }

    @Override
    public void add(ShortURL newURL) {
        
        String url = newURL.getOriginal();
        int rawKey = newURL.getPath();
        String key = String.valueOf(rawKey);
        
        try (Jedis jedis = new Jedis(hostname)) {
            switchDb(jedis);
            jedis.set(key, url);
        }
        
    }

    @Override
    public ShortURL findbyId(int id) throws ShortURLNotFoundException {
        
        String key = String.valueOf(id);
        
        try (Jedis jedis = new Jedis(hostname)) {
            switchDb(jedis);
            
            if (!jedis.exists(key))
                throw new ShortURLNotFoundException("no matching short url found in Redis DB");
            
            String value = jedis.get(key);
            return new ShortURL(id,value);
        }
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
