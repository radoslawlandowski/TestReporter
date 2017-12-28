package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import testreporter.client.DAO.TestGroupDao;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.enums.ResultFileTypes;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestRun;
import testreporter.core.services.FileUtils;
import testreporter.core.services.parser.TestRunParserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Path("/test-groups/{testGroupName}/test-runs")
public class TestRunResource {

    private TestRunDao testRunDao;
    private TestGroupDao testGroupDao;
    private TestRunParserFactory testRunParserFactory;
    private FileUtils fileUtils;

    public TestRunResource(TestRunDao testRunDao, TestGroupDao testGroupDao, TestRunParserFactory testRunParserFactory, FileUtils fileUtils) {
        this.testRunDao = testRunDao;
        this.testGroupDao = testGroupDao;
        this.testRunParserFactory = testRunParserFactory;
        this.fileUtils = fileUtils;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Timed
    @UnitOfWork
    public Response uploadFile(
            @PathParam("testGroupName") String testGroupName,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {

        Optional<TestGroup> testGroup = this.testGroupDao.find(testGroupName);

        if(!testGroup.isPresent()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The specified test group: " + testGroupName + " does not exist!")
                    .build();
        }

        ResultFileTypes resultFileType = ResultFileTypes.getResultFileType(fileDetail.getFileName());

        TestRun testRun = testRunParserFactory.create(resultFileType).parseResult(uploadedInputStream);

        testRun.setTestGroup(testGroup.get());
        testRunDao.create(testRun);

        return Response.ok().build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<TestRun> get(@PathParam("testGroupName") String testGroupName) {
        return testRunDao.findByGroupName(testGroupName);
    }

}
