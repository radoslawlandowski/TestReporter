package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import testreporter.client.DAO.TestGroupDao;
import testreporter.core.models.TestGroup;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test-group")
public class TestGroupResource {

    private TestGroupDao testGroupDao;

    public TestGroupResource(TestGroupDao testGroupDao) {
        this.testGroupDao = testGroupDao;
    }

    @POST
    @Timed
    @UnitOfWork
    public Response create(TestGroup testGroup) {

        this.testGroupDao.create(testGroup);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    @Path("/all")
    public Response get() {
        return Response.ok().entity(testGroupDao.findAll()).allow("OPTIONS").build();// testGroupDao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public TestGroup getByName(@QueryParam("test-group-name") String testGroupName)  {
        return testGroupDao.findByGroupName(testGroupName);
    }
}
