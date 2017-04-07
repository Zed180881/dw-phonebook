
package com.softserveinc.phonebook.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserveinc.phonebook.api.User;
import com.softserveinc.phonebook.service.UserService;

@RolesAllowed("ADMIN")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        User user = userService.getUserById(id);
        return Response.ok(user).build();
    }

    @GET
    @Path("/getName")
    public Response getUserByName(@QueryParam("name") @NotBlank String name) {
        User user = userService.getUserByName(name);
        if (user == null) {
            return Response.noContent().build();
        }
        return Response.ok(user).build();
    }

    @GET
    public Response getUserList() {
        List<User> users = userService.getAllUsers();
        return Response.ok(users).build();
    }

    @POST
    public Response createUser(User user) throws URISyntaxException {
        int newUserId = userService.createUser(user);
        return Response.created(new URI(String.valueOf(newUserId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id, User user) {
        user.setId(id);
        userService.updateUser(user);
        return Response.ok(user).build();
    }

}
