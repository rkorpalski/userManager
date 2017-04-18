package br.com.decora.resources;

import br.com.decora.controller.AuthenticationController;
import br.com.decora.controller.UserController;
import br.com.decora.entity.User;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UserResource {

    @Inject
    private UserController userController;

    @Inject
    private AuthenticationController authController;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpHeaders httpHeaders){
        try{
            String userName = httpHeaders.getHeaderString(HttpHeadersName.USERNAME_HEADER);
            String password = httpHeaders.getHeaderString(HttpHeadersName.PASSWORD_HEADER);
            String authToken = authController.login(userName, password);
            return Response.ok(authToken).build();
        }catch (LoginException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        try {
            List<User> users = userController.findAllUsers();
            return Response.ok(users).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/persistUser")
    public Response persistUser(User user){
        try {
            userController.persistUser(user);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/logout")
    public Response logout(@Context HttpHeaders httpHeaders){
        try {
            String authToken = httpHeaders.getHeaderString(HttpHeadersName.AUTHTOKEN_HEADER);
            authController.logout(authToken);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
