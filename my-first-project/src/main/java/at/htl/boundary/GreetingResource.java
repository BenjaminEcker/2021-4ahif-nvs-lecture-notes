package at.htl.boundary;

import at.htl.control.GreetingService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("greet")
public class GreetingResource {
    
    //private static final Logger logger = Logger.getLogger(GreetingResource.class.getSimpleName());
    
    @Inject
    Logger logger;
    
    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String greeting(@PathParam("name") String name) {
        logger.logf(Logger.Level.ERROR, "******************* %s", name);
        return greetingService.greeting(name);
    }

}
