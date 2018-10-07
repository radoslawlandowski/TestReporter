package api.services.handler;


import api.model.models.File;
import api.model.models.TestCase;
import api.model.models.TestRun;
import api.services.filter.TestCaseFilterManager;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentHandler {

    public final static String ATTACHMENT_PROPERTY_NAME = "Attachment";

    public TestRun handleAttachments(TestRun testRun, List<File> attachments) {
        List<TestCase> testCasesWithAttachments = new TestCaseFilterManager(testRun)
                .getTestCases()
                .whichHaveAttachments()
                .done();

        testCasesWithAttachments.stream()
                .forEach(testCase -> {
                    testCase.getProperties()
                            .stream()
                            .filter(property -> property.getName().equals(ATTACHMENT_PROPERTY_NAME))
                            .forEach(property -> {
                                File f = findByName(attachments, property.getValue()).get(0);
                                Integer attachmentId = this.sendAttachment(f);
                                property.setValue(attachmentId.toString());
                            });
                });

        return testRun;
    }

    public List<File> findByName(List<File> files, String filename) {
        return files.stream().filter(file -> file.getFileName().equals(filename)).collect(Collectors.toList());
    }

    private Integer sendAttachment(File file) {

        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        ByteArrayResource bar = new ByteArrayResource(file.getData()) {
            @Override
            public String getFilename() {
                return file.getFileName();
            }
        };

        bodyMap.add("file", bar);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer> response = restTemplate.postForEntity("http://localhost:11113/attachments",
                requestEntity, Integer.class);

        return response.getBody();
    }
}
