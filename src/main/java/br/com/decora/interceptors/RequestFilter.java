package br.com.decora.interceptors;

import br.com.decora.controller.AuthenticationController;
import br.com.decora.resources.HttpHeadersName;

import javax.inject.Inject;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class RequestFilter implements ContainerRequestFilter{

    @Inject
    private AuthenticationController authController;
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String path = containerRequestContext.getUriInfo().getPath();

        if(containerRequestContext.getRequest().getMethod().equals("OPTIONS")){
            containerRequestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }

        if (!path.startsWith("/login")){
            String authToken = containerRequestContext.getHeaderString(HttpHeadersName.AUTHTOKEN_HEADER);

            if(!authController.isAuthorizationTokenValid(authToken)){
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }
}
