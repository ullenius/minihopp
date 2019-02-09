/**
 * 
 * REST-server implementation
 * 
 */
package se.anosh.minihopp.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
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

    
}
