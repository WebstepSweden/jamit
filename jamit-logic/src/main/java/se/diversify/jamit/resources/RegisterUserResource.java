package se.diversify.jamit.resources;

import org.apache.commons.lang3.StringUtils;
import scala.Tuple2;
import se.diversify.jamit.domain.User;
import se.diversify.jamit.repository.UserDao;
import se.diversify.jamit.util.EncryptionUtils;
import se.diversify.jamit.util.JsonUtils;

import javax.ws.rs.*;

import static se.diversify.jamit.resources.FormUtils.*;

/**
 * REST service for registering new users
 */
@Path("/register-user")
public class RegisterUserResource {

    private UserDao dao = UserDao.defaultDao();

    /**
     * Register a new user using the POST REST service. The request should include following arguments in
     * application/x-www-form-urlencoded format
     *
     * @param name      the user's name
     * @param email     the user's email address
     * @param phone     the user's phone no
     * @param role      the user's role
     * @param password1 the user's password
     * @param password2 the user's password entered again
     * @return the new created user, in JSOn format, if the registration went well, or a not ok message, also in JSON
     *         format, if anything went wrong
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String register(@FormParam("name") String name, @FormParam("email") String email,
                           @FormParam("phone") String phone, @FormParam("role") String role, @FormParam("password1") String password1,
                           @FormParam("password2") String password2) {
        String result = "";

        Tuple2<Boolean, String> passwordsOk = isPasswordsOk(password1, password2);
        Tuple2<Boolean, String> fieldsOk = isFieldsMissing(new String[][]{{"name", name}, {"email", email}, {"phone", phone}, {"role", role}, {"password1", password1}, {"password2", password2}});

        if (!passwordsOk._1()) {
            result = JsonUtils.notOk(passwordsOk._2());
        } else if (!fieldsOk._1()) {
            result = JsonUtils.notOk(fieldsOk._2());
        } else {
            User user = dao.add(User.create(name, email, phone, role, EncryptionUtils.encrypt(password1)));
            result = JsonUtils.toJson(user);
        }
        return result;
    }


}
