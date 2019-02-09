package se.anosh.minihopp.domain;

import java.net.URL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Entity class used by JPA
 * 
 * 
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Entity
@XmlRootElement
public class ShortURL {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @XmlElement(name = "short_url")
    private int path; // this value is set by the database
    
    @XmlElement(name = "original_url")
    private URL original;

    public ShortURL() { // empty constructor required by JPA
        
    }
    
    public ShortURL(URL urlToShorten) {
        original = urlToShorten;
    }

    public int getPath() {
        return path;
    }

    public URL getOriginal() {
        return original;
    }

    public void setOriginal(URL original) {
        this.original = original;
    }
    
}
