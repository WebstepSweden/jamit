package se.diversify.jamit.resources;

import org.bson.types.ObjectId;
import se.diversify.jamit.domain.User;
import se.diversify.jamit.repository.UserDao;
import se.diversify.jamit.util.JsonUtils;

import javax.ws.rs.*;

@Path("/user/{id}")
public class UserResource {

    private UserDao dao = UserDao.defaultDao();

    @GET
    @Produces("application/json")
    public String get(@PathParam("id") String id) {
        String result = "";
        try {
            User user = dao.get(new ObjectId(id));
            result = JsonUtils.toJson(user);
        } catch (Exception e) {
            result = JsonUtils.notOk(e);
        }
        return result;
    }
}
