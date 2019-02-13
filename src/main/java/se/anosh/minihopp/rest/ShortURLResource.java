/**
 * 
 * REST-server implementation
 * 
 */
package se.anosh.minihopp.rest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import se.anosh.minihopp.api.ShortURLService;
import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Stateless
@Path("/minihopp")
public class ShortURLResource {
    
    
    @Inject
    private ShortURLService service;
    
    
    @GET
    @Produces({"application/JSON"})
    public Response getNoArgument() {
        return Response.status(404).build(); // not found
    }
    
    @GET
    @Produces({"application/JSON"})
    @Path("{shortURL}")
    public Response getRedirect(@PathParam("shortURL") int shortURL) {
        
        try {
            ShortURL result = service.findURL(shortURL);
            URI uri = new URI(result.getOriginal());
            return Response.seeOther(uri).build();
            
        } catch (ShortURLNotFoundException ex) {
            return Response.status(400).entity(new ErrorMessage("invalid URL")).build();
        } catch (URISyntaxException er) {
             //URI stored in database is invalid, data is corrupted
            return Response.status(500).entity(new ErrorMessage("database corrupted")).build();
        }
    }
    
    /**
     * 
     * Acceps a String as POST-input
     * returns a JSON-object of type ShortURL
     * 
     * @param url
     * @return
     * @throws MalformedURLException 
     */
    @POST
    @Produces({"application/JSON"})
    @Consumes(MediaType.TEXT_PLAIN)
    public Response postURL(String url) {
        
        // Checks if the URL is valid, if so adds it to the database
        // Need to add checking if it already exists
        try {
            URL address = new URL(url);
            service.addURL(address);
            return Response.ok(service.findShortURLName(url)).build();
        } catch (MalformedURLException ex) {
            return Response.status(400).entity(new ErrorMessage("invalid URL")).build();
        }
        catch (ShortURLNotFoundException eu) {
            // something went terribly wrong
            // we added the url but we can't find it when fetching it from
            // the database. 500 - internal server error
            return Response.status(500).entity(new ErrorMessage("database corrupted")).build(); 
        }
        
        
    }
    
    @XmlRootElement
    private class ErrorMessage {
        
        @XmlElement(name = "error")
        final private String message;
        public ErrorMessage(String message) {
            this.message = message;
        }
    }
    
}
