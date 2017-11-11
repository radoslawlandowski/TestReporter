package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.FileManager.IFileManager;
import testreporter.core.IXmlDeserializer;
import testreporter.core.TestRunDeserializer;
import testreporter.core.models.TestRun;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Path("/file")
public class FileResource {

    private TestRunDao testRunDao;

    public FileResource(TestRunDao testRunDao) {
        this.testRunDao = testRunDao;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Timed
    @UnitOfWork
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException, JAXBException {

        IXmlDeserializer des = new TestRunDeserializer();
        TestRun testRun = (TestRun)des.deserialize(uploadedInputStream);

        this.testRunDao.create(testRun);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<TestRun> get() {
        return testRunDao.findAll();
    }
}
