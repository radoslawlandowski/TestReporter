package attachments.controller;

import attachments.model.Attachment;
import attachments.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AttachmentController {

    @Autowired
    AttachmentRepository repository;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/attachments/{fileId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> byId(@PathVariable("fileId") int fileId) {
        Attachment a = repository.findById(fileId).get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + a.getAttachmentName() + "\"")
                .body(a.getData());
    }

    @RequestMapping(value = "/attachments",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> upload(@RequestParam("file") MultipartFile file) throws IOException {
        Attachment a = new Attachment();
        a.setAttachmentName(file.getOriginalFilename());
        a.setData(file.getBytes());

        int id = repository.save(a).getId();

        return ResponseEntity.ok().body(Integer.valueOf(id));
    }
}

//
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Timed
//    @UnitOfWork
//    public Response uploadFile(
//            @PathParam("testGroupName") String testGroupName,
//            @FormDataParam("file") InputStream uploadedInputStream,
//            @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {


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
