package testreporter.client.DAO;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import testreporter.core.models.TestRun;

import java.util.List;

public class TestRunDao extends AbstractDAO<TestRun> {

    public TestRunDao(SessionFactory factory) {
        super(factory);
    }

    public List<TestRun> findAll() {
        return list(namedQuery("TestRun.findAll"));
    }

    public List<TestRun> findByGroupName(String testGroupName) {
        return list(namedQuery("TestRun.find").setParameter("testGroupName", testGroupName));
    }

    public long create(TestRun testRun) {
        return persist(testRun).getId();
    }
}
