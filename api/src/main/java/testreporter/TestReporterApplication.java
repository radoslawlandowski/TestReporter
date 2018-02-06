package testreporter;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import testreporter.client.DAO.AttachmentDao;
import testreporter.client.DAO.TestGroupDao;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.services.handler.AttachmentHandler;
import testreporter.core.services.handler.UploadedTestResultsHandler;
import testreporter.core.services.deserializer.TestRunDeserializer;
import testreporter.core.models.*;
import testreporter.core.services.unzipper.FileUnzipper;
import testreporter.core.services.validator.UploadedFilesValidator;
import testreporter.resources.AttachmentResource;
import testreporter.resources.filters.CORSFIlter;
import testreporter.resources.TestRunResource;
import testreporter.resources.TestGroupResource;

public class TestReporterApplication extends Application<TestReporterConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TestReporterApplication().run(args);
    }

    @Override
    public String getName() {
        return "testreporter";
    }

    @Override
    public void initialize(final Bootstrap<TestReporterConfiguration> bootstrap) {
        final HbnBundle hibernate = new HbnBundle();
        // register hbn bundle before guice to make sure factory initialized before guice context start
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig("testreporter")
                .modules(new HbnModule(hibernate))
                .useWebInstallers()
                .build());
    }

    @Override
    public void run(final TestReporterConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(MultiPartFeature.class);
    }

}
