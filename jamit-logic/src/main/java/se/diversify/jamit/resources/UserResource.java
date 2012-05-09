package se.diversify.jamit.resources;

import se.diversify.jamit.repository.UserDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@Path("/user/{userId}")
public class UserResource {

    @Context
    UriInfo uriInfo;

    private UserDao dao = UserDao.defaultDao();


    @GET
    @Produces("text/plain")
    public String getUser(@PathParam("userId") int id) {
        return dao.get(id).toString();
    }
}
