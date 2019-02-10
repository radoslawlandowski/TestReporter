package api.controller;

import api.model.enums.ResultFileTypes;
import api.model.models.TestGroup;
import api.model.models.TestResults;
import api.model.models.TestRun;
import api.repository.TestGroupRepository;
import api.repository.TestRunRepository;
import api.services.deserializer.TestRunDeserializer;
import api.services.handler.AttachmentHandler;
import api.services.handler.UploadedTestResultsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@RestController()
@CrossOrigin(origins = "*")
public class TestRunController {

    @Autowired private TestRunRepository testRunRepository;
    @Autowired private TestGroupRepository testGroupRepository;
    @Autowired private TestRunDeserializer testRunDeserializer;
    @Autowired private UploadedTestResultsHandler uploadedTestResultsHandler;
    @Autowired private AttachmentHandler attachmentHandler;

    @RequestMapping(value = "/test-groups/{testGroupName}/test-runs/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity byId(@PathVariable("id") int id) {

        return ResponseEntity.status(HttpStatus.OK).body(testRunRepository.findById(id));
    }

    @RequestMapping(value = "/test-groups/{testGroupName}/test-runs",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload(@PathVariable("testGroupName") String testGroupName,
            @RequestParam("file") MultipartFile file) throws Exception {
        Optional<TestGroup> testGroup = Optional.ofNullable(testGroupRepository.findByName(testGroupName));

        if(!testGroup.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{ \"message\": \"The specified test group: " + testGroupName + " does not exist!\"}");
        }

        ResultFileTypes resultFileType = ResultFileTypes.getResultFileType(file.getOriginalFilename());

        TestResults testResults = uploadedTestResultsHandler.getTestResult(new ByteArrayInputStream(file.getBytes()), resultFileType);

        TestRun testRun = testRunDeserializer.deserialize(testResults.getXmlFile().getData());

        testResults.getAttachments().ifPresent(atts -> attachmentHandler.handleAttachments(testRun, atts));

        testRun.setTestGroup(testGroup.get());
        TestRun createdTestRun = testRunRepository.save(testRun);

        return ResponseEntity.status(HttpStatus.OK).body(createdTestRun.getId());
    }
}
