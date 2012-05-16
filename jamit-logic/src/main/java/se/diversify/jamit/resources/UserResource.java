package se.diversify.jamit.resources;

import se.diversify.jamit.domain.Role;
import se.diversify.jamit.domain.User;
import se.diversify.jamit.repository.UserDao;
import se.diversify.jamit.util.JsonUtils;
import se.diversify.jamit.util.EncryptionUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST service definitions of path /user/&ltid&gt
 */
@Path("/user/{userId}")
public class UserResource {

    private UserDao dao = UserDao.defaultDao();

    /**
     * Retrieve a user using the GET REST service, given a user id
     *
     * @param id the user id, included in the URL
     * @return the user, represented in JSON format, or an Exception, also in JSON format, if anything went wrong.
     */
    @GET
    @Produces("application/json")
    public String get(@PathParam("userId") int id) {
        String result = "";
        try {
            result = JsonUtils.toJson(dao.get(id));
        } catch (Exception e) {
            result = JsonUtils.toJson(e);
        }
        return result;
    }

    /**
     * Update a user using the POST REST service. The request should include following arguments in
     * application/x-www-form-urlencoded format
     *
     * @param id       the user's id
     * @param name     the user's name
     * @param email    the user's email address
     * @param role     the user's role
     * @param password the user's password
     * @return the updated user, in JSON format, or an Exception, also in JSON format, if anything went wrong.
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String update(@FormParam("id") String id, @FormParam("name") String name, @FormParam("email") String
            email, @FormParam("role") String role, @FormParam("password") String password) {
        String result = "";
        try {
            User user = new User(Integer.valueOf(id), name, email, role, EncryptionUtils.encrypt(password));
            user = dao.update(user);
            result = JsonUtils.toJson(user);
        } catch (Exception e) {
            result = JsonUtils.toJson(e);
        }
        return result;
    }

    /**
     * Delete a user using the DELETE REST service, given the user id.
     *
     * @param id the id of the user to delete, included in the URL
     * @return an Ok message in JSON format, or an Exception, also in JSON format, if anything went wrong.
     */
    @DELETE
    @Produces("application/json")
    public String delete(@PathParam("id") String id) {
        String result = JsonUtils.ok();
        try {
            dao.delete(Integer.valueOf(id));
        } catch (IllegalArgumentException e) {
            result = JsonUtils.toJson(e);
        }
        return result;
    }
}


