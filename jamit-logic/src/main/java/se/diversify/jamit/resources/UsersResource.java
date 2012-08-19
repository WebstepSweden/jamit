package se.diversify.jamit.resources;

import se.diversify.jamit.domain.User;
import se.diversify.jamit.repository.UserDao;
import se.diversify.jamit.util.JsonUtils;
import se.diversify.jamit.util.ListConverter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/users")
public class UsersResource {

    private UserDao dao = UserDao.defaultDao();

    @GET
    @Produces("application/json")
    public String getAll() {
        List<?> users = ListConverter.toJava(dao.getAll());
        return JsonUtils.toJson(users);
    }
}
