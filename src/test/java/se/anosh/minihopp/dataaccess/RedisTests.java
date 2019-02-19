package se.anosh.minihopp.dataaccess;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import se.anosh.minihopp.dataaccess.api.ShortURLDataAccess;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public class RedisTests {
    
    private  ShortURLDataAccess resource;
    
    @Before
    public void setup() {
        
        resource = new RedisShortURL();
    }
    
    @Test
    public void testRemoveMethod() {
       resource.remove(1337);
        
    }
    
}
