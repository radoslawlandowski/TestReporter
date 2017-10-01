package testreporter;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadTests {

    @ClassRule
    public static final DropwizardAppRule<TestReporterConfiguration> RULE =
            new DropwizardAppRule<TestReporterConfiguration>(TestReporterApplication.class, ResourceHelpers.resourceFilePath("config.yml"));

    @Test
    public void UploadedFileIsDownloadable() throws IOException {

        JerseyClientConfiguration configuration = new JerseyClientConfiguration();
        configuration.setChunkedEncodingEnabled(false);

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).using(configuration).build("test client");

        FileDataBodyPart filePart = new FileDataBodyPart("file", new File(ResourceHelpers.resourceFilePath("test-results/nunit-3-test-result.xml")));
        FormDataMultiPart multiPart = new FormDataMultiPart();
        multiPart.bodyPart(filePart);

        Response response = client.target(
                String.format("http://localhost:%d/file", RULE.getLocalPort()))
                .register(MultiPartFeature.class)
                .request()
                .post(Entity.entity(multiPart, multiPart.getMediaType()));

        assertThat(response.getStatus()).isEqualTo(200);

        
    }
}
