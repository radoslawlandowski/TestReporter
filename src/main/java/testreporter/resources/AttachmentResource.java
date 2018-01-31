package testreporter.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import testreporter.client.DAO.AttachmentDao;
import testreporter.core.models.File;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/attachments")
public class AttachmentResource {

    @Inject private AttachmentDao attachmentDao;

    @GET
    @Timed
    @UnitOfWork
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response create(@QueryParam("fileId") Integer fileId) {
        File f = attachmentDao.getAttachment(fileId);

        return Response.ok(f.getData())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", f.getFileName()))
                .build();
    }


}
