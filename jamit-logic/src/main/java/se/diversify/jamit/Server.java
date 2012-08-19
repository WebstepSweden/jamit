package se.diversify.jamit;


import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Start a server that runs the RESt service
 */
public class Server {//extends PackagesResourceConfig {
    public Server() {
        //super("se.diversify.jamit.resources");
    }


    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/jamit-logic").port(8080).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {
        System.out.println("Starting grizzly...");
        ResourceConfig rc = new PackagesResourceConfig("se.diversify.jamit.resources");
        return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nTry out %susers\nHit enter to stop it...",
                BASE_URI, BASE_URI));
        System.in.read();

        httpServer.stop();
    }

}
