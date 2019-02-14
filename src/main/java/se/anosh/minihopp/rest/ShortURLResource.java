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
import java.util.Objects;
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
import se.anosh.minihopp.controller.ShortURLService;
import se.anosh.minihopp.dataaccess.exception.ShortURLNotFoundException;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Stateless
@Path("/minihopp")
public class ShortURLResource {
    
    // HTTP-status codes
    private static final int BAD_REQUEST = 400;
    private static final int NOT_FOUND = 404;
    private static final int INTERNAL_SERVER_ERROR = 500;
    
    
    @Inject
    private ShortURLService service;
    
    /**
     * 
     * @return 
     */
    @GET
    @Produces({"application/JSON"})
    public Response getNoArgument() {
        return Response.status(BAD_REQUEST).entity(new ErrorMessage("no URL provided")).build();
    }
    
    /**
     * Returns full URL as HTTP-redirect Response-object
     * based on path-parameter obtained from user.
     * 
     * @param shortURL
     * @return 
     */
    @GET
    @Produces({"application/JSON"})
    @Path("{shortURL}")
    public Response getRedirect(@PathParam("shortURL") int shortURL) {
        
        try {
            ShortURL result = service.findURL(shortURL);
            URI uri = new URI(result.getOriginal());
            return Response.seeOther(uri).build();
            
        } catch (ShortURLNotFoundException ex) {
            return Response.status(NOT_FOUND).entity(new ErrorMessage("URL not found")).build();
        } catch (URISyntaxException er) {
             //URI stored in database is invalid, data is corrupted
            return Response.status(INTERNAL_SERVER_ERROR).entity(new ErrorMessage("database corrupted")).build();
        }
    }
    
    /**
     * 
     * Acceps a String as POST-input
     * returns a JSON-object of type ShortURL
     * 
     * @param url
     * @return
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
            return Response.status(BAD_REQUEST).entity(new ErrorMessage("invalid URL")).build();
        }
        catch (ShortURLNotFoundException eu) {
            // something went terribly wrong
            // we added the url but we can't find it when fetching it from
            // the database. 500 - internal server error
            return Response.status(INTERNAL_SERVER_ERROR).entity(new ErrorMessage("database corrupted")).build(); 
        }
        
        
    }
    
    /**
     * This class' sole purpose is to contain
     * an error String. Then the whole object
     * is converted into XML or JSON by JAX-RS.
     * 
     * Rather than having to hard-code JSON
     * or XML.
     * 
     * This class is immutable by using dependency injection.
     */
    @XmlRootElement
    private class ErrorMessage {
        
        @XmlElement(name = "error")
        final private String message;
        public ErrorMessage(String message) {
            this.message = Objects.requireNonNull(message);
        }
        @Override
        public String toString() {
            return "message: " + message;
        }
    }
    
}
