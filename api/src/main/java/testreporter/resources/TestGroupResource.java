package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import testreporter.client.DAO.TestGroupDao;
import testreporter.core.models.TestGroup;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test-groups")
public class TestGroupResource {

    @Inject private TestGroupDao testGroupDao;

    @POST
    @Timed
    @UnitOfWork
    public Response create(TestGroup testGroup) {
        TestGroup createdGroup = this.testGroupDao.create(testGroup);

        return Response.ok().entity(createdGroup).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Response get() {
        return Response.ok().entity(testGroupDao.findAll()).allow("OPTIONS").build();
    }

    @GET
    @Path("/{testGroupName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public TestGroup getByName(@PathParam("testGroupName") String testGroupName) {
        return testGroupDao.find(testGroupName)
                .orElseThrow(() -> new NotFoundException("Test group of this name does not exist"));
    }
}
