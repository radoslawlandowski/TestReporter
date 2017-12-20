package testreporter.core.services.parser;

import testreporter.core.exceptions.MoreThanOneResultFileException;
import testreporter.core.services.deserializer.IXmlDeserializer;
import testreporter.core.models.ResultFile;
import testreporter.core.models.TestCase;
import testreporter.core.models.TestRun;
import testreporter.core.services.filter.TestCaseFilterManager;
import testreporter.core.services.unzipper.IFileUnzipper;
import testreporter.core.services.validator.FileUploadValidator;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;

public class ZippedTestRunParser extends TestRunParser {

    public final static String ATTACHMENT_PROPERTY_NAME = "Attachment";
    public final static String XML_EXTENSION = "xml";

    protected IFileUnzipper fileUnzipper;
    protected FileUploadValidator fileUploadValidator;

    public ZippedTestRunParser(IXmlDeserializer deserializer, IFileUnzipper fileUnzipper, FileUploadValidator fileUploadValidator) {
        super(deserializer);

        this.fileUnzipper = fileUnzipper;
        this.fileUploadValidator = fileUploadValidator;
    }

    @Override
    public TestRun parseResult(InputStream resultStream) throws IOException, JAXBException, MoreThanOneResultFileException {
        List<ResultFile> unzippedFiles = fileUnzipper.unzip(resultStream);

        if(!fileUploadValidator.isValid(unzippedFiles)) {
            throw new IOException("Invalid result file package");
        }

        ResultFile file = fileUnzipper.findByExtension(unzippedFiles, XML_EXTENSION).stream().findFirst().get();

        TestRun testRun = super.deserialize(new ByteArrayInputStream(file.getData()));

        return handleAttachments(testRun, unzippedFiles);
    }

    protected TestRun handleAttachments(TestRun testRun, List<ResultFile> unzippedFiles) {
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
                                property.setResultFile(fileUnzipper.findByName(unzippedFiles, property.getValue()).get(0));
                            });
                });

        return testRun;
    }

}
