package se.diversify.jamit.resources;

import se.diversify.jamit.domain.User;
import se.diversify.jamit.repository.UserDao;
import se.diversify.jamit.util.EncryptionUtils;
import se.diversify.jamit.util.JsonUtils;

import javax.ws.rs.*;

/**
 * REST service definitions of path /users
 */
@Path("/users")
public class UsersResource {

    private UserDao dao = UserDao.defaultDao();

    /**
     * Retrieves all users using the GET REST service.
     *
     * @return all users, represented in JSON format, or an Exception, also in JSON format, if anything went wrong.
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        String result = "";
        try {
            result = JsonUtils.toJson(dao.getAll());
        } catch (Exception e) {
            result = JsonUtils.toJson(e);
        }
        return result;
    }

    /**
     * Add a new user using the PUT REST service.  The request should include following arguments in
     * application/x-www-form-urlencoded format
     *
     * @param name     the user's name
     * @param email    the user's email address
     * @param role     the user's role
     * @param password the user's password
     * @return the new user, in JSON format, or an Exception, also in JSON format, if anything went wrong.
     */
    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String add(@FormParam("name") String name, @FormParam("email") String email, @FormParam("role") String role,
                      @FormParam("password") String password) {
        String result = "";
        try {
            User user = new User(0, name, email, role, EncryptionUtils.encrypt(password));
            user = dao.add(user);
            result = JsonUtils.toJson(user);
        } catch (Exception e) {
            result = JsonUtils.toJson(e);
        }
        return result;
    }
}

