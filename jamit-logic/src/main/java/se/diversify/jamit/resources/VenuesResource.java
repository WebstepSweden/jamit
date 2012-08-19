package se.diversify.jamit.resources;

import se.diversify.jamit.domain.Venue;
import se.diversify.jamit.repository.VenueDao;
import se.diversify.jamit.util.JsonUtils;
import se.diversify.jamit.util.ListConverter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/venues")
public class VenuesResource {

    private VenueDao dao = VenueDao.defaultDao();

    @GET
    @Produces("application/json")
    public String getAll() {
        List<Venue> venues = ListConverter.toJava(dao.getAll());
        return JsonUtils.toJson(venues);
    }
}
