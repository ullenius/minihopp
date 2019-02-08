package se.anosh.minihopp.dataaccess;

import java.net.URL;
import java.util.List;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public interface URLdataAccess {
    
    public void remove(int id);
    public void add();
    public void insert(URL newUrl);
    public URL findbyId(int id);
    public URL findByName(String name);
    public List<URL> findAll();
}
