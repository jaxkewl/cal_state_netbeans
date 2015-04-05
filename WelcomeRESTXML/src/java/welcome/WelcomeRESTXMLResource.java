/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcome;

import java.io.StringWriter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.xml.bind.JAXB;

/**
 * REST Web Service
 *
 * @author martin
 */
@Path("welcome")
public class WelcomeRESTXMLResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WelcomeRESTXMLResource
     */
    public WelcomeRESTXMLResource() {
    }

    /**
     * Retrieves representation of an instance of welcome.WelcomeRESTXMLResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{name}")
    @Produces("application/xml")
    public String getXml(@PathParam("name") String name) {
        String message = "Welcome to JAX-RS web service with REST and XML " + name + "!";
        StringWriter writer = new StringWriter();
        JAXB.marshal(message, writer);
        return writer.toString();
    }

    /**
     * PUT method for updating or creating an instance of WelcomeRESTXMLResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
