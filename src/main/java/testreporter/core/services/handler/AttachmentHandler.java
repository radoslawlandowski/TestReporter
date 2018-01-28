package testreporter.core.services.handler;

import testreporter.core.models.File;
import testreporter.core.models.TestCase;
import testreporter.core.models.TestRun;
import testreporter.core.services.filter.TestCaseFilterManager;

import java.util.List;
import java.util.stream.Collectors;

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
                                property.setFile(findByName(attachments, property.getValue()).get(0));
                            });
                });

        return testRun;
    }


    public List<File> findByName(List<File> files, String filename) {
        return files.stream().filter(file -> file.getFileName().equals(filename)).collect(Collectors.toList());
    }
}
