package testreporter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import testreporter.core.FileManager.FileManager;
import testreporter.resources.FileResource;

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
        // TODO: application initialization
    }

    @Override
    public void run(final TestReporterConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(MultiPartFeature.class);

        environment.jersey().register(
                new FileResource(
                        new FileManager(
                                configuration.getResultsFolder()
                        )
                )
        );
    }

}
