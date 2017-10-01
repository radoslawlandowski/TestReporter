package testreporter;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadTest {

    @ClassRule
    public static final DropwizardAppRule<TestReporterConfiguration> RULE =
            new DropwizardAppRule<TestReporterConfiguration>(TestReporterApplication.class, ResourceHelpers.resourceFilePath("config.yml"));

    public Client client;
    public File resultsFile;
    public String fileResourcePath;

    @Before
    public void Before() {
        JerseyClientConfiguration configuration = new JerseyClientConfiguration();
        configuration.setChunkedEncodingEnabled(false);

        client = new JerseyClientBuilder(RULE.getEnvironment())
                .using(configuration)
                .build("test client");

        resultsFile = new File(ResourceHelpers.resourceFilePath("test-results/nunit-3-test-result.xml"));

        fileResourcePath = String.format("http://localhost:%d/file", RULE.getLocalPort());
    }

    @Test
    public void UploadedFileIsDownloadable() throws IOException {

        FileDataBodyPart filePart = new FileDataBodyPart("file", resultsFile);
        FormDataMultiPart multiPart = new FormDataMultiPart();
        multiPart.bodyPart(filePart);

        Response uploadFileResponse = client.target(fileResourcePath)
                .register(MultiPartFeature.class)
                .request()
                .post(Entity.entity(multiPart, multiPart.getMediaType()));

        assertThat(uploadFileResponse.getStatus()).isEqualTo(200);

        InputStream downloadedFileStream = client.target(fileResourcePath)
                .queryParam("name", resultsFile.getName())
                .request()
                .get(InputStream.class);

        Object[] resultsFileLines = Files.readAllLines(Paths.get(resultsFile.getPath())).toArray();

        BufferedReader downloadedFileReader = new BufferedReader(new InputStreamReader(downloadedFileStream));

        String line;
        int index = 0;
        while ((line = downloadedFileReader.readLine()) != null) {
            assertThat(line).isEqualTo(resultsFileLines[index]);

            index++;
        }
    }
}
