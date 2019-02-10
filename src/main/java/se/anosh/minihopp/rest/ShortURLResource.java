/**
 * 
 * REST-server implementation
 * 
 */
package se.anosh.minihopp.rest;

import java.net.MalformedURLException;
import java.net.URL;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import se.anosh.minihopp.ShortURLService;
import se.anosh.minihopp.domain.ShortURL;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
@Stateless
@Path("/minihopp")
public class ShortURLResource {
    
    // error message in JSON-format
    final static private String ERROR_MESSAGE = "{\"error\":\"invalid URL\"}";
    @Inject
    private ShortURLService service;
    
    
    @GET
    @Produces({"application/JSON"})
    public Response getNoArgument() {
        
        return Response.ok(ERROR_MESSAGE).build();
    }
    
    @GET
    @Produces({"application/JSON"})
    @Path("{shortURL}")
    public Response getRedirect(@PathParam("shortURL") Integer shortURL) {
        
        if (shortURL == null)
            return Response.ok(ERROR_MESSAGE).build();
        
        ShortURL result = service.findURL(shortURL);
        return Response.ok(result).build();
        
    }
    
    @POST
    @Consumes({"application/JSON"})
    public Response postURL(String url) throws MalformedURLException {
        
        // Checks if the URL is valid, if so adds it to the database
        // Need to add checking if it already exists
        try {
            URL address = new URL(url);
            service.addURL(address);
            return Response.ok(service.findShortURLName(url)).build();
        } catch (MalformedURLException ex) {
            return Response.ok(ERROR_MESSAGE).build();
        }
        
        
    }
    

    
}
