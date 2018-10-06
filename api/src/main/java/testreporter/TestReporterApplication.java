package testreporter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import testreporter.resources.AttachmentResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");    }

}
