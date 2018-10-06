package attachments.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttachmentController {

    @RequestMapping(value = "/attachments", method = RequestMethod.GET)
    public String byId() {
        return "Greetings from Spring Boot!";
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
//                        String.format("attachment; filename=\"%s\"", f.getFileName()))
//                .build();
//    }
//
//
//}
