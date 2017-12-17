package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import testreporter.client.DAO.TestGroupDao;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.enums.TestRunParserTypes;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestRun;
import testreporter.core.services.parser.TestRunParserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("/test-run")
public class TestRunResource {

    private TestRunDao testRunDao;
    private TestGroupDao testGroupDao;
    private TestRunParserFactory testRunParserFactory;

    public TestRunResource(TestRunDao testRunDao, TestGroupDao testGroupDao, TestRunParserFactory testRunParserFactory) {
        this.testRunDao = testRunDao;
        this.testGroupDao = testGroupDao;
        this.testRunParserFactory = testRunParserFactory;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Timed
    @UnitOfWork
    public Response uploadFile(
            @QueryParam("test-group-name") String testGroupName,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {

        TestGroup testGroup = this.testGroupDao.findByGroupName(testGroupName);

        if(testGroup == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The specified test group: " + testGroupName + " does not exist!")
                    .build();
        }

        String fileExtension = fileDetail.getFileName().split("\\.")[1];
        TestRunParserTypes parserType = TestRunParserTypes.getParserTypeForFile(fileExtension);

        TestRun testRun = testRunParserFactory.create(parserType).parseResult(uploadedInputStream);
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
