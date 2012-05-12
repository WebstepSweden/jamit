package se.diversify.jamit.resources;

import se.diversify.jamit.domain.Role;
import se.diversify.jamit.domain.User;
import se.diversify.jamit.repository.UserDao;
import se.diversify.jamit.util.JsonUtils;
import se.diversify.jamit.util.EncryptionUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/user/{userId}")
public class UserResource {

    private UserDao dao = UserDao.defaultDao();

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

    @DELETE
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String delete(@FormParam("id") String id) {
        String result = JsonUtils.ok();
        try {
            dao.delete(Integer.valueOf(id));
        } catch (IllegalArgumentException e) {
            result = JsonUtils.toJson(e);
        }
        return result;
    }
}


