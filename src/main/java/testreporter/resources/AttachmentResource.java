package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import testreporter.client.DAO.AttachmentDao;
import testreporter.core.models.ResultFile;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/attachment")
public class AttachmentResource {

    private AttachmentDao attachmentDao;

    public AttachmentResource(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

    @GET
    @Timed
    @UnitOfWork
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response create(@QueryParam("fileId") Integer fileId) {
        ResultFile f = attachmentDao.getAttachment(fileId);

        return Response.ok(f.getData())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", f.getFileName()))
                .build();
    }


}
