package se.diversify.jamit.resources;

import se.diversify.jamit.repository.UserDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/users")
public class UsersResource {

    private UserDao dao = UserDao.defaultDao();

    @GET
    @Produces("text/plain")
    public String getAllUsers() {
        return dao.getAll().toString();
    }
}

