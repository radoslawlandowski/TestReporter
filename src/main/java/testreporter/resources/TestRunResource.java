package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.dropwizard.hibernate.UnitOfWork;
import jdk.nashorn.internal.parser.JSONParser;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import testreporter.client.DAO.TestGroupDao;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.enums.ResultFileTypes;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestResults;
import testreporter.core.models.TestRun;
import testreporter.core.services.handler.AttachmentHandler;
import testreporter.core.services.handler.UploadedTestResultsHandler;
import testreporter.core.services.deserializer.TestRunDeserializer;

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
    private TestRunDeserializer testRunDeserializer;
    private UploadedTestResultsHandler uploadedTestResultsHandler;
    private AttachmentHandler attachmentHandler;

    public TestRunResource(TestRunDao testRunDao,
                           TestGroupDao testGroupDao,
                           TestRunDeserializer testRunDeserializer,
                           UploadedTestResultsHandler uploadedTestResultsHandler,
                           AttachmentHandler attachmentHandler) {
        this.testRunDao = testRunDao;
        this.testGroupDao = testGroupDao;
        this.testRunDeserializer = testRunDeserializer;
        this.uploadedTestResultsHandler = uploadedTestResultsHandler;
        this.attachmentHandler = attachmentHandler;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Response uploadFile(
            @PathParam("testGroupName") String testGroupName,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {

        Optional<TestGroup> testGroup = this.testGroupDao.find(testGroupName);

        if(!testGroup.isPresent()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{ \"message\": \"The specified test group: " + testGroupName + " does not exist!\"}")
                    .build();
        }

        ResultFileTypes resultFileType = ResultFileTypes.getResultFileType(fileDetail.getFileName());

        TestResults testResults = uploadedTestResultsHandler.getTestResult(uploadedInputStream, resultFileType);

        TestRun testRun = testRunDeserializer.deserialize(testResults.getXmlFile().getData());

        testResults.getAttachments().ifPresent(atts -> attachmentHandler.handleAttachments(testRun, atts));

        testRun.setTestGroup(testGroup.get());
        testRunDao.create(testRun);

        return Response.ok().entity("{\"message\": \"File uploaded!\"}").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<TestRun> get(@PathParam("testGroupName") String testGroupName) {
        return testRunDao.findByGroupName(testGroupName);
    }

}
