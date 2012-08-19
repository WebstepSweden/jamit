package se.diversify.jamit.resources;

import se.diversify.jamit.domain.Jam;
import se.diversify.jamit.domain.Venue;
import se.diversify.jamit.repository.JamDao;
import se.diversify.jamit.util.JsonUtils;
import se.diversify.jamit.util.ListConverter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/jams")
public class JamsResource {

    private JamDao dao = JamDao.defaultDao();

    @GET
    @Produces("application/json")
    public String getAll() {
        List<Jam> jams = ListConverter.toJava(dao.getAll());
        return JsonUtils.toJson(jams);
    }
}
