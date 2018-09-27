package testreporter.client.DAO;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;
import testreporter.core.models.TestRun;

import java.util.List;

@EagerSingleton
public class TestRunDao extends AbstractDAO<TestRun> {

    @Inject
    public TestRunDao(SessionFactory factory) {
        super(factory);
    }

    public List<TestRun> findAll() {
        return list(namedQuery("TestRun.findAll"));
    }

    public List<TestRun> findByGroupName(String testGroupName) {
        return list(namedQuery("TestRun.find").setParameter("testGroupName", testGroupName));
    }

    public TestRun create(TestRun testRun) {
        return persist(testRun);
    }
}
