package testreporter;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import javassist.tools.reflect.Sample;
import testreporter.core.models.*;

public class HbnBundle extends HibernateBundle<TestReporterConfiguration> {

    public HbnBundle() {
        super(
                Failure.class,
                File.class,
                Property.class,
                TestCase.class,
                TestGroup.class,
                TestResults.class,
                TestRun.class,
                TestSuite.class
        );
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(TestReporterConfiguration testReporterConfiguration) {
        return testReporterConfiguration.getDataSourceFactory();
    }
}
