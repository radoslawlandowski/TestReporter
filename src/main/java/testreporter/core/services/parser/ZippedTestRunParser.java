package testreporter.core.services.parser;

import testreporter.core.services.deserializer.IXmlDeserializer;
import testreporter.core.models.ResultFile;
import testreporter.core.models.TestCase;
import testreporter.core.models.TestRun;
import testreporter.core.services.unzipper.IFileUnzipper;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ZippedTestRunParser extends TestRunParser {

    public final static String XML_EXTENSION = ".xml";
    public final static String ATTACHMENT_PROPERTY_NAME = "Attachment";

    protected IFileUnzipper fileUnzipper;

    public ZippedTestRunParser(IXmlDeserializer deserializer, IFileUnzipper fileUnzipper) {
        super(deserializer);

        this.fileUnzipper = fileUnzipper;
    }

    @Override
    public TestRun parseResult(InputStream resultStream) throws IOException, JAXBException {
        List<ResultFile> unzippedFiles = unzip(resultStream);

        ResultFile resultFile = findResultFile(unzippedFiles);

        TestRun testRun = super.deserialize(new ByteArrayInputStream(resultFile.getData()));

        List<TestCase> testCasesWithAttachments = new ArrayList<>();

        testRun.getTestSuite()
                .getTestSuites()
                .stream()
                .filter(Objects::nonNull)
                .filter(testSuite -> testSuite.getTestCases() != null)
                .forEach(testSuite -> {
                    testSuite.getTestCases()
                            .stream()
                            .filter(Objects::nonNull)
                            .filter(testCase -> (testCase.getProperties()) != null)
                            .forEach(testCaseWithProps -> {
                                boolean hasAtts = testCaseWithProps.getProperties()
                                        .stream()
                                        .anyMatch(property -> property.getName().equals(ATTACHMENT_PROPERTY_NAME));

                                if(hasAtts) {
                                    testCasesWithAttachments.add(testCaseWithProps);
                                }
                            });
                });

        testCasesWithAttachments.stream()
                .forEach(testCase -> {
                    testCase.getProperties()
                            .stream()
                            .filter(property -> property.getName().equals(ATTACHMENT_PROPERTY_NAME))
                            .forEach(property -> {
                                ResultFile resFile = unzippedFiles.stream()
                                        .filter(file -> file.getFileName().equals(property.getValue()))
                                        .findFirst()
                                        .get();

                                testCase.setResultFile(resFile);
                            });
                });

        return testRun;
    }

    private List<ResultFile> unzip(InputStream resultStream) throws IOException {
        return fileUnzipper.unzip(resultStream);
    }

    private ResultFile findResultFile(List<ResultFile> streams) {
        return streams.stream().filter(item -> item.getFileName().contains(XML_EXTENSION)).collect(Collectors.toList()).get(0);
    }
}
