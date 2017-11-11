package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import testreporter.client.DAO.TestGroupDao;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.IXmlDeserializer;
import testreporter.core.TestRunDeserializer;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestRun;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
    public List<TestGroup> get() {
        return testGroupDao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public TestGroup getByName(@QueryParam("test-group-name") String testGroupName)  {
        return testGroupDao.findByGroupName(testGroupName);
    }
}
