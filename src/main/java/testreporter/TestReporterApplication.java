package testreporter;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import testreporter.client.DAO.TestGroupDao;
import testreporter.client.DAO.TestRunDao;
import testreporter.core.models.*;
import testreporter.resources.TestRunResource;
import testreporter.resources.TestGroupResource;

public class TestReporterApplication extends Application<TestReporterConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TestReporterApplication().run(args);
    }

    private final HibernateBundle<TestReporterConfiguration> hibernate = new HibernateBundle<TestReporterConfiguration>(TestRun.class, TestSuite.class, TestCase.class, Property.class, Failure.class, TestGroup.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TestReporterConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "testreporter";
    }

    @Override
    public void initialize(final Bootstrap<TestReporterConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final TestReporterConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(MultiPartFeature.class);

        final TestRunDao testRunDao = new TestRunDao(hibernate.getSessionFactory());
        final TestGroupDao testGroupDao = new TestGroupDao(hibernate.getSessionFactory());

        environment.jersey().register(new TestRunResource(testRunDao, testGroupDao));
        environment.jersey().register(new TestGroupResource(testGroupDao));

    }

}
