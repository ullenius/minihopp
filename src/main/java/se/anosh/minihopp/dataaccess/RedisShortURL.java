/**
 * 
 * Redis NoSQL implementation of the DAO-interface
 * 
 * Borde vara singleton? REdis är single-threaded? Nej den har lösningar för sånt
 * 
 */
package se.anosh.minihopp.dataaccess;

import java.util.ArrayList;
import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.ejb.Stateless;
import redis.clients.jedis.Jedis;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import se.anosh.minihopp.domain.ShortURL;
import se.anosh.minihopp.dataaccess.api.ShortURLDataAccess;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Alternative
@Stateless
public class RedisShortURL implements ShortURLDataAccess {
    
    private static int counter = 1;
    
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

    /**
     * 
     * This method ignores the provided id field
     * in the parameter. Instead using only the class'
     * internal static counter to generate ids at the DB level
     * 
     * @param newURL
     * @return 
     */
    @Override
    public Optional<Integer> add(ShortURL newURL) {
        
        String url = newURL.getLongFormatURL();
        String key = Integer.toString(counter);
        
        try (Jedis jedis = new Jedis(hostname)) {
            switchDb(jedis);
            jedis.set(key, url);
        }
        
        counter++; // increase id counter
        return Optional.of(counter-1);
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
        
        List<ShortURL> myList = new ArrayList<>();
        
        try (Jedis jedis = new Jedis(hostname)) {
            switchDb(jedis);
            Set<String> allKeys = jedis.keys("*");
            
            for (String key : allKeys) {
                int urlKey = Integer.parseInt(key);
                String value = jedis.get(key);
                myList.add(new ShortURL(urlKey, value));
            }
            return myList;
        }
    }
    
}
