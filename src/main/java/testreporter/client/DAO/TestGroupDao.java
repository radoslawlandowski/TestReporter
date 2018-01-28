package testreporter.client.DAO;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestRun;

import java.util.List;
import java.util.Optional;

public class TestGroupDao extends AbstractDAO<TestGroup> {

    public TestGroupDao(SessionFactory factory) {
        super(factory);
    }

    public List<TestGroup> findAll() {
        return list(namedQuery("TestGroup.findAll"));
    }

    public Optional<TestGroup> find(String testGroupName) {
        return Optional.ofNullable(uniqueResult(namedQuery("TestGroup.findByTestGroupName").setParameter("testGroupName", testGroupName)));
    }

    public long create(TestGroup testRun) {
        return persist(testRun).getId();
    }
}
