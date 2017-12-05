package testreporter.client.DAO;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import testreporter.core.models.TestGroup;
import testreporter.core.models.TestRun;

import java.util.List;

public class TestGroupDao extends AbstractDAO<TestGroup> {

    public TestGroupDao(SessionFactory factory) {
        super(factory);
    }

    public List<TestGroup> findAll() {
        return list(namedQuery("TestGroup.findAll"));
    }

    public TestGroup findByGroupName(String testGroupName) {
        return uniqueResult(namedQuery("TestGroup.findByTestGroupName").setParameter("testGroupName", testGroupName));
    }

    public long create(TestGroup testRun) {
        return persist(testRun).getId();
    }
}
