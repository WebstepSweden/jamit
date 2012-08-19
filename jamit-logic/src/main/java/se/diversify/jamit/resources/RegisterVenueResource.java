package se.diversify.jamit.resources;

import scala.Tuple2;
import se.diversify.jamit.domain.User;
import se.diversify.jamit.domain.Venue;
import se.diversify.jamit.repository.VenueDao;
import se.diversify.jamit.util.EncryptionUtils;
import se.diversify.jamit.util.JsonUtils;

import javax.ws.rs.*;

import static se.diversify.jamit.resources.FormUtils.isFieldsMissing;

/**
 * REST service for registering new venues
 */
@Path("/register-venue")
public class RegisterVenueResource {

    private VenueDao dao = VenueDao.defaultDao();

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String register(@FormParam("name") String name,
                           @FormParam("address") String address,
                           @FormParam("postalCode") String postalCode,
                           @FormParam("city") String city,
                           @FormParam("publicEvent") boolean publicEvent,
                           @FormParam("maxNoOfMusicians") int maxNoOfMusicians,
                           @FormParam("contact") String contact) {

        String result = "";
        Tuple2<Boolean, String> fieldsOk = isFieldsMissing(new String[][]{{"address", address}, {"city", city}, {"contact", contact}});

        if (!fieldsOk._1()) {
            result = JsonUtils.notOk(fieldsOk._2());
        } else {
            Venue venue = dao.add(Venue.create(name, address, postalCode, city, publicEvent, maxNoOfMusicians, contact));
            result = JsonUtils.toJson(venue);
        }
        return result;
    }

}

