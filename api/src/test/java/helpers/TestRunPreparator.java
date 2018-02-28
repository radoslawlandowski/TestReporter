package helpers;

import testreporter.core.enums.Result;
import testreporter.core.models.Property;
import testreporter.core.models.TestCase;
import testreporter.core.models.TestRun;
import testreporter.core.models.TestSuite;
import testreporter.core.services.filter.criteria.AttachmentPropertyCriteria;

import java.util.ArrayList;
import java.util.List;

public class TestRunPreparator {
    protected TestRun testRun;

    public TestRunPreparator() {
    }

    public TestRunPreparator createTestRun() {
        create();

        return this;
    }

    public TestRunPreparator withTestSuite() {
        TestSuite testSuite = new TestSuite();
        testSuite.setTestCases(new ArrayList<>());

        this.testRun.getTestSuite().getTestSuites().add(testSuite);

        return this;
    }

    public TestRunPreparator withTestCases(Result ...results) {

        List<TestCase> testCases = new ArrayList<>();
        for(Result r : results) {
            TestCase testCase = new TestCase();
            testCase.setResult(r.getResult());
            testCase.setProperties(new ArrayList<>());
            testCases.add(testCase);
        }

        TestSuite testSuite = getLastTestSuite();
        testSuite.getTestCases().addAll(testCases);

        return this;
    }

    public TestRunPreparator whichHasAttachment() {
        Property property = new Property();

        property.setName(AttachmentPropertyCriteria.ATTACHMENT_PROPERTY_NAME);

        getLastTestCase().getProperties().add(property);

        return this;
    }

    public TestRun done() {
        return this.testRun;
    }

    private void create() {
        this.testRun = new TestRun();
        this.testRun.setTestSuite(new TestSuite());
        this.testRun.getTestSuite().setTestSuites(new ArrayList<>());
    }

    private TestSuite getLastTestSuite() {
        return this.testRun.getTestSuite().getTestSuites().stream().reduce((first, last) -> last).get();
    }

    private TestCase getLastTestCase() {
        return getLastTestSuite().getTestCases().stream().reduce((first, last) -> last).get();
    }
}
