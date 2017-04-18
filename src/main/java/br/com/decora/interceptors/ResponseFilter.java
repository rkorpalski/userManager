package br.com.decora.interceptors;

import br.com.decora.resources.HttpHeadersName;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add( "Access-Control-Allow-Origin", "*" );
        containerResponseContext.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        containerResponseContext.getHeaders().add( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        containerResponseContext.getHeaders().add( "Access-Control-Allow-Headers", HttpHeadersName.AUTHTOKEN_HEADER);
    }
}
