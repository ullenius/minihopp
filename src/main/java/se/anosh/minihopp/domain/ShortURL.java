package se.anosh.minihopp.domain;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;
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
public class ShortURL implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @XmlElement(name = "short_url")
    private int path; // this value is set by the database
    
    @XmlElement(name = "original_url")
    @Column(unique=true, nullable=false, length=100)
    private String original;
    
    public ShortURL() { // empty constructor required by JPA
        original = "default";
    }
    
    public ShortURL(URL url) {
        original = url.toString();
    }

    public int getPath() {
        return path;
    }

    public String getOriginal() {
        return original;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.original);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShortURL other = (ShortURL) obj;
        return (this.original.equalsIgnoreCase(other.getOriginal()));
    }

    
    
    
    
}
