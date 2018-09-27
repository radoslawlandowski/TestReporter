package testreporter.client.DAO;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestRun;

import java.util.List;
import java.util.Optional;

@EagerSingleton
public class TestGroupDao extends AbstractDAO<TestGroup> {

    @Inject
    public TestGroupDao(SessionFactory factory) {
        super(factory);
    }

    public List<TestGroup> findAll() {
        return list(namedQuery("TestGroup.findAll"));
    }

    public Optional<TestGroup> find(String testGroupName) {
        return Optional.ofNullable(uniqueResult(namedQuery("TestGroup.findByTestGroupName").setParameter("testGroupName", testGroupName)));
    }

    public TestGroup create(TestGroup testRun) {
        return persist(testRun);
    }
}
