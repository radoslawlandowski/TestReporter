package testreporter.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
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
import java.util.stream.Collectors;

@Path("/file")
public class FileResource {

    private IFileManager fileManager;

    public FileResource(IFileManager fileManager) {
        this.fileManager = fileManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@QueryParam("name") String name) {

        StreamingOutput fileStream = new StreamingOutput()
        {
            @Override
            public void write(java.io.OutputStream downloadedOutputStream) throws IOException, WebApplicationException
            {
                fileManager.get(name, downloadedOutputStream);
            }
        };

        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = " + name)
                .build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException, JAXBException {

        IXmlDeserializer des = new TestRunDeserializer();
        TestRun result = (TestRun)des.deserialize(uploadedInputStream);

        return Response.ok().build();
    }
}
