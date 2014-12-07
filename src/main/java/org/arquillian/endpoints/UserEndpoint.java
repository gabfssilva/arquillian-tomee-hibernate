package org.arquillian.endpoints;

import org.arquillian.converters.UserConverter;
import org.arquillian.converters.UserResourceConverter;
import org.arquillian.entities.User;
import org.arquillian.repositories.UserRepository;
import org.arquillian.resources.UserResource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
@Path(UserEndpoint.PEOPLE_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint {
    public static final String PEOPLE_PATH = "/people";

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserResourceConverter userResourceConverter;

    @Inject
    private UserConverter userConverter;

    @GET
    public Response fetch(){
        return Response
                    .ok(userRepository.findAll())
                    .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UserResource userResource) throws URISyntaxException {
        User user = userConverter.convert(userResource);
        user = userRepository.save(user);

        return Response
                    .created(new URI(PEOPLE_PATH + "/" + user.getId()))
                    .entity(userResourceConverter.convert(user))
                    .build();
    }

    @GET
    @Path("{id}")
    public Response fetchOne(@PathParam("id") Long id) throws URISyntaxException {
        User user = userRepository.fetch(id);

        if(user == null){
            return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
        }

        return Response
                    .ok(userResourceConverter.convert(user))
                    .build();
    }
}
