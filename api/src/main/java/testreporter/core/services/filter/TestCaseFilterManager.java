package testreporter.core.services.filter;

import testreporter.core.enums.Result;
import testreporter.core.models.*;
import testreporter.core.services.filter.criteria.AttachmentPropertyCriteria;
import testreporter.core.services.filter.criteria.Criteria;
import testreporter.core.services.filter.criteria.PropertyCriteria;
import testreporter.core.services.filter.criteria.ResultCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestCaseFilterManager {
    private TestRun testRun;
    private List<TestCase> testCases;

    public TestCaseFilterManager(TestRun testRun) {
        this.testRun = testRun;
        this.testCases = new ArrayList<>();
    }

    public TestCaseFilterManager getTestCases() {
        TestSuite mainSuite = this.testRun.getTestSuite();

        mainSuite.getTestSuites()
                .stream()
                .filter(Objects::nonNull)
                .filter(testSuite -> testSuite.getTestCases() != null)
                .forEach(testSuite -> testCases.addAll(testSuite.getTestCases()));

        if(mainSuite.getTestCases() != null) {
            testCases.addAll(mainSuite.getTestCases());
        }

        return this;
    }

    public TestCaseFilterManager whichFailed() {
        return meetCriteria(new ResultCriteria(Result.Failed));
    }

    public TestCaseFilterManager whichPassed() {
        return meetCriteria(new ResultCriteria(Result.Passed));
    }

    public TestCaseFilterManager whichHaveAttachments() {
        return meetCriteria(new AttachmentPropertyCriteria());
    }

    public TestCaseFilterManager whichHaveProperties() {
        return meetCriteria(new PropertyCriteria());
    }

    public List<TestCase> done() {
        return testCases;
    }

    protected TestCaseFilterManager meetCriteria(Criteria criteria) {
        testCases = criteria.meetCriteria(testCases);

        return this;
    }

}
