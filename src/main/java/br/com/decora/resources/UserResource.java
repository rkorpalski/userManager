package br.com.decora.resources;

import br.com.decora.controller.UserController;
import br.com.decora.entity.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UserResource {

    @Inject
    private UserController userController;

    @POST
    @Path("/login")
    public Response login(){
        return Response.ok().build();
    }

    @GET
    @Path("/getAllUsers")
    public Response getAllUsers(){
        List<User> users =  userController.findAllUsers();
        return Response.ok(users).build();
    }

    @POST
    @Path("/persistUser")
    public Response persistUser(User user){
        userController.persistUser(user);
        return Response.ok().build();
    }
}
