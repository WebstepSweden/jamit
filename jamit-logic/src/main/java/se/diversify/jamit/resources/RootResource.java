package se.diversify.jamit.resources;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.File;
import java.io.IOException;

/**
 * Service shown at the root / of the application, allows testing the rest services
 */
@Path("")
public class RootResource {

    @GET
    public String root() throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream("/root.html"));
    }
}
