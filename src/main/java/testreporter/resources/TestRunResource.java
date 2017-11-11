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
import java.io.InputStream;
import java.util.List;

@Path("/test-run")
public class TestRunResource {

    private TestRunDao testRunDao;
    private TestGroupDao testGroupDao;

    public TestRunResource(TestRunDao testRunDao, TestGroupDao testGroupDao) {
        this.testRunDao = testRunDao;
        this.testGroupDao = testGroupDao;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Timed
    @UnitOfWork
    public Response uploadFile(
            @QueryParam("test-group-name") String testGroupName,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws JAXBException, WebApplicationException {

        TestGroup testGroup = this.testGroupDao.findByGroupName(testGroupName);

        IXmlDeserializer des = new TestRunDeserializer();

        TestRun testRun = (TestRun)des.deserialize(uploadedInputStream);
        if(testGroup == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The specified test group: " + testGroupName + " does not exist!")
                    .build();
        }

        testRun.setTestGroup(testGroup);

        testRunDao.create(testRun);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    @Path("/all")
    public List<TestRun> get() {
        return testRunDao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<TestRun> getByName(@QueryParam("test-group-name") String testGroupName) {
        return testRunDao.findByGroupName(testGroupName);
    }
}
