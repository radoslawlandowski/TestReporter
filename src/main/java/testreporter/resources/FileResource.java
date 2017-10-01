package testreporter.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import testreporter.core.FileManager.IFileManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;

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
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        fileManager.save(uploadedInputStream, fileDetail.getFileName());

        return Response.ok().build();
    }
}
