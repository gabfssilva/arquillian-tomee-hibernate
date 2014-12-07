package org.arquillian.converters;

import org.arquillian.entities.User;
import org.arquillian.resources.UserResource;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class UserConverter implements Converter<UserResource, User> {
    @Override
    public User convert(UserResource userResource) {
        User.UserBuilder userBuilder = User.newBuilder();

        userBuilder
                .cpf(userResource.getCpf())
                .name(userResource.getName())
                .occupation(userResource.getOccupation());

        return userBuilder.build();
    }
}
