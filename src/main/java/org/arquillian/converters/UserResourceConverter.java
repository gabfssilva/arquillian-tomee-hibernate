package org.arquillian.converters;

import org.arquillian.entities.User;
import org.arquillian.resources.UserResource;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class UserResourceConverter implements Converter<User, UserResource> {
    @Override
    public UserResource convert(User user) {
        UserResource.UserResourceBuilder userResourceBuilder = UserResource.newBuilder();

        userResourceBuilder
                .id(user.getId())
                .cpf(user.getCpf())
                .name(user.getName())
                .occupation(user.getOccupation());

        return userResourceBuilder.build();
    }
}
