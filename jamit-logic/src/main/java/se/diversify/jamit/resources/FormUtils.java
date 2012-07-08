package se.diversify.jamit.resources;

import org.apache.commons.lang.StringUtils;
import scala.Tuple2;

/**
 * Resource util class
 */
public class FormUtils {

    static Tuple2<Boolean, String> isPasswordsOk(String password1, String password2) {
        boolean ok = true;
        String message = "";

        if (StringUtils.isBlank(password1)) {
            ok = false;
            message = "password1 is blank";
        }
        if (StringUtils.isBlank(password2)) {
            ok = false;
            message += (StringUtils.isEmpty(message) ? "" : ", ") + "password2 is blank";
        }
        if (!password1.equals(password2)) {
            ok = false;
            message += (StringUtils.isEmpty(message) ? "" : ", ") + "passwords don't match";
        }

        return new Tuple2<Boolean, String>(ok, message);
    }

    static Tuple2<Boolean, String> isFieldsMissing(String[][] fields) {
        boolean ok = true;
        String message = "";

        for (String[] field : fields) {
            if (StringUtils.isBlank(field[1])) {
                ok = false;
                message += (StringUtils.isEmpty(message) ? "" : ", ") + field[0] + " is missing";
            }
        }

        return new Tuple2<Boolean, String>(ok, message);
    }
}
