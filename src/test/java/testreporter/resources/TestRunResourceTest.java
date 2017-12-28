package testreporter.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import testreporter.client.DAO.TestGroupDao;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.models.Property;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestRun;
import testreporter.core.services.FileUtils;
import testreporter.core.services.deserializer.TestRunDeserializer;
import testreporter.core.services.parser.TestRunParserFactory;
import testreporter.core.services.unzipper.FileUnzipper;

import javax.ws.rs.core.Response;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestRunResourceTest {

    public static String RAW_RESULT_FILE = "src/main/resources/results/nunit-13-test-result.xml";
    public static String ZIP_RESULT_FILE = "src/main/resources/results/test-run-1/test-run-1.zip";

    protected TestRunResource testRunResource;

    @Mock protected TestRunDao testRunDaoMock;
    @Mock protected TestGroupDao testGroupDaoMock;
    @Mock protected TestGroup testGroupMock;

    @Before
    public void setUp() throws Exception {
        TestRunParserFactory testRunParserFactory = new TestRunParserFactory(new TestRunDeserializer(), new FileUnzipper());
        FileUtils fileUtils = new FileUtils();

        testRunResource = new TestRunResource(testRunDaoMock, testGroupDaoMock, testRunParserFactory, fileUtils);
    }

    @Test
    public void checkIfUploadedRawFileIsParsedProperly() {

        FormDataContentDisposition fileDetailsMock = mock(FormDataContentDisposition.class);
        InputStream inputStream = getFileAsStream(RAW_RESULT_FILE);
        String groupName = "anyName";
        String fileName = "file.xml";

        when(fileDetailsMock.getFileName()).thenReturn(fileName);
        when(testGroupDaoMock.findByGroupName(anyString())).thenReturn(testGroupMock);

        Response r = null;
        try {
            r = testRunResource.uploadFile(groupName, inputStream, fileDetailsMock);
        } catch(Exception e) {
            fail("Exception thrown");
        }

        ArgumentCaptor<TestRun> argumentCaptor = ArgumentCaptor.forClass(TestRun.class);
        verify(testRunDaoMock).create(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName()).isEqualTo("mock-assembly.dll");

        assertThat(r.getStatus()).isEqualTo(200);
    }

    @Test
    public void checkIfUploadedZipFileAttachmentsAreHandledProperly() {

        FormDataContentDisposition fileDetailsMock = mock(FormDataContentDisposition.class);
        InputStream inputStream = getFileAsStream(ZIP_RESULT_FILE);
        String groupName = "anyName";
        String fileName = "file.zip";

        when(fileDetailsMock.getFileName()).thenReturn(fileName);
        when(testGroupDaoMock.findByGroupName(anyString())).thenReturn(testGroupMock);

        Response r = null;
        try {
            r = testRunResource.uploadFile(groupName, inputStream, fileDetailsMock);
        } catch(Exception e) {
            fail("Exception thrown");
        }

        ArgumentCaptor<TestRun> argumentCaptor = ArgumentCaptor.forClass(TestRun.class);
        verify(testRunDaoMock).create(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName()).isEqualTo("mock-assembly.dll");

        Property meaningfulAttachment = getMeaningfulAttachment(argumentCaptor);
        assertThat(meaningfulAttachment.getName()).isEqualTo("Attachment");
        assertThat(meaningfulAttachment.getValue()).isEqualTo("result-pic-1.jpg");
        assertThat(meaningfulAttachment.getResultFile().getFileName()).isEqualTo("result-pic-1.jpg");
        assertThat(meaningfulAttachment.getResultFile().getData().length).isGreaterThan(0);

        assertThat(r.getStatus()).isEqualTo(200);
    }

    private InputStream getFileAsStream(String filePath) {
        File initialFile = new File(filePath);

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            fail("The requested resource file does not exist!");
        }

        return inputStream;
    }

    private Property getMeaningfulAttachment(ArgumentCaptor<TestRun> argumentCaptor) {
        return argumentCaptor.getValue().getTestSuite().getTestSuites().get(0).getTestCases().get(2).getProperties().get(1);
    }

}
