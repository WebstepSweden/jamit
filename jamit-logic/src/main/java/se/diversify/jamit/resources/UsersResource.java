package se.diversify.jamit.resources;

import se.diversify.jamit.domain.User;
import se.diversify.jamit.repository.UserDao;
import se.diversify.jamit.util.EncryptionUtils;
import se.diversify.jamit.util.JsonUtils;

import javax.ws.rs.*;

@Path("/users")
public class UsersResource {

    private UserDao dao = UserDao.defaultDao();

    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return JsonUtils.toJson(dao.getAll());
    }

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

