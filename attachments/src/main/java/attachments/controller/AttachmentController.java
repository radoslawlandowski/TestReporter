package attachments.controller;

import attachments.model.Attachment;
import attachments.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttachmentController {

    @Autowired
    AttachmentRepository repository;

    @RequestMapping(value = "/attachments", method = RequestMethod.GET)
    public Attachment byId() {
        Attachment a = new Attachment();

        return repository.save(a);
    }

}

//@Path("/attachments")
//public class AttachmentController {
//
//    @Inject private AttachmentDao attachmentDao;
//
//    @GET
//    @Timed
//    @UnitOfWork
//    @Produces(MediaType.APPLICATION_OCTET_STREAM)
//    public Response create(@QueryParam("fileId") Integer fileId) {
//        File f = attachmentDao.getAttachment(fileId);
//
//        return Response.ok(f.getData())
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        String.format("attachment; filename=\"%s\"", f.getAttachmentName()))
//                .build();
//    }
//
//
//}
