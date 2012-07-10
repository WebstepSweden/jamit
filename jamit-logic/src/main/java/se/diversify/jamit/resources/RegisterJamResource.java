package se.diversify.jamit.resources;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import scala.Tuple2;
import se.diversify.jamit.domain.Jam;
import se.diversify.jamit.domain.Venue;
import se.diversify.jamit.repository.JamDao;
import se.diversify.jamit.repository.VenueDao;
import se.diversify.jamit.util.JsonUtils;

import javax.ws.rs.*;

import static se.diversify.jamit.resources.FormUtils.isFieldsMissing;

@Path("/register-jam")
public class RegisterJamResource {

    private JamDao dao = JamDao.defaultDao();

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String register(@FormParam("time") String time,
                         @FormParam("venueId")ObjectId venueId){

        String result = "";
        Tuple2<Boolean, String> fieldsOk = isFieldsMissing(new String[][]{{"time", time}, {"venueId", venueId.toString()}});

        if (!fieldsOk._1()) {
            result = JsonUtils.notOk(fieldsOk._2());
        } else {
            VenueDao venueDao = VenueDao.defaultDao();
            Venue venue = venueDao.get(venueId);

            Jam jam = dao.add(Jam.create(DateTime.parse(time), venue));
            result = JsonUtils.toJson(jam);
        }
        return result;

    }

}
