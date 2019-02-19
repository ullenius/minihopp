package se.anosh.minihopp.dataaccess;


import java.net.MalformedURLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import se.anosh.minihopp.dataaccess.api.ShortURLDataAccess;
import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public class RedisTests {
    
    private  ShortURLDataAccess resource;
    
    @Before
    public void setup() {
        
        resource = new RedisShortURL("localhost",2);
    }
    
    @Test(expected = ShortURLNotFoundException.class)
    public void testRemoveFromDatabase() throws ShortURLNotFoundException {
      
       resource.remove(1337);
       resource.findbyId(1337);
       
    }
    
    @Test
    public void testAddToDatabase() {
        
        String rawURL = "https://www.reddit.com/r/3DS";
        ShortURL url = new ShortURL();
        url.setPath(1336);
        try {
            url.setOriginal(rawURL);
            resource.add(url);
            
        } catch (MalformedURLException ex) {
            fail();
        }
        
    }
    
    @Test
    public void testFindExistingById() {
        
        try {
        ShortURL result = resource.findbyId(1336);
        System.out.println("testFindExistingById: ");
        System.out.println(result);
        } catch (ShortURLNotFoundException ex) {
            fail();
        }
        
    }
    
    @Test(expected = ShortURLNotFoundException.class)
    public void testFindNonExistingById() throws ShortURLNotFoundException {
        
        resource.findbyId(666);
    }
    
    /**
     * Test fails if there are no entries found in the database
     * 
     */
    @Test
    public void testFindAllShortURLsInDataBase() {
        
        List<ShortURL> listOfAll = resource.findAll();
        System.out.println("Running testFindAllShortURLsInDatabase:");
        for (ShortURL url : listOfAll)
            System.out.println(url);
        
        assertFalse(listOfAll.isEmpty());
    }
    
}
