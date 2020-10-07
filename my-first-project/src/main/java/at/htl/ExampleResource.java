package at.htl;

import at.htl.entity.Person;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello 3ahif! -> " + LocalDateTime.now();
    }

    @GET
    @Path("/person")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Person findPerson() {
        return new Person("Bozidar");
    }

    @GET
    @Path("/p")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findPersonx() {
        return Response
                .accepted(new Person("Fabsi"))
                .header("tag","Bocki was here")
                .build();
    }
    @GET
    @Path("/p2")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findPersony() {
        return Json.createObjectBuilder().add("name","Susi").build();
    }

    @POST
    @Path("p3")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String foo(Person person) {
        return "Hello " + person.getName();
    }

    @POST
    @Path("p4")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person p4(JsonObject personJson) {
        return new Person(personJson.getString("nachname"));
    }
}