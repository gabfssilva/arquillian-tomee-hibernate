package org.arquillian.endpoints;

import org.arquillian.entities.Person;
import org.arquillian.repositories.PersonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Path(PersonEndpoint.PEOPLE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PersonEndpoint {
    public static final String PEOPLE_PATH = "/people";

    @Inject
    private PersonRepository personRepository;

    @GET
    public Response fetch(){
        return Response
                    .ok(personRepository.findAll())
                    .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Person person) throws URISyntaxException {
        person = personRepository.save(person);

        return Response
                .created(new URI(PEOPLE_PATH+"/"+person.getId()))
                .entity(person)
                .build();
    }

    @GET
    @Path("{id}")
    public Response fetchOne(@PathParam("id") Long id) throws URISyntaxException {
        Person person = personRepository.fetch(id);

        if(person == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response
                .ok(person)
                .build();
    }
}
