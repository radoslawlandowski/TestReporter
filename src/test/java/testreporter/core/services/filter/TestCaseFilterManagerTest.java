package testreporter.core.services.filter;

import org.junit.Before;
import org.junit.Test;
import helpers.TestRunPreparator;
import testreporter.core.enums.Result;
import testreporter.core.models.TestCase;
import testreporter.core.models.TestRun;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCaseFilterManagerTest {

    protected TestRun testRun;
    protected TestCaseFilterManager filterManager;

    @Before
    public void setUp() throws Exception {
        this.testRun = prepareTestRun();
        this.filterManager = new TestCaseFilterManager(this.testRun);
    }

    @Test
    public void checkIfAllTestCasesAreReturned() {
        List<TestCase> testCases = filterManager.getTestCases().done();

        assertThat(testCases.size()).isEqualTo(9);
    }

    @Test
    public void checkIfFailedTestCasesAreReturned() {
        List<TestCase> testCases = filterManager.getTestCases().whichFailed().done();

        assertThat(testCases.size()).isEqualTo(4);
    }

    @Test
    public void checkIfPassedTestCasesAreReturned() {
        List<TestCase> testCases = filterManager.getTestCases().whichPassed().done();

        assertThat(testCases.size()).isEqualTo(5);
    }

    @Test
    public void checkIfTestCasesWithAttachmentsAreReturned() {
        List<TestCase> testCases = filterManager.getTestCases().whichHaveAttachments().done();

        assertThat(testCases.size()).isEqualTo(1);
    }

    @Test
    public void checkIfTestCasesWithPropertiesAreReturned() {
        List<TestCase> testCases = filterManager.getTestCases().whichHaveProperties().done();

        assertThat(testCases.size()).isEqualTo(1);
    }

    private TestRun prepareTestRun() {
        TestRunPreparator testRunPreparator = new TestRunPreparator();

        TestRun testRun = testRunPreparator.createTestRun()
                .withTestSuite()
                .withTestCases(Result.Passed, Result.Passed, Result.Passed, Result.Passed, Result.Passed)
                .withTestSuite()
                .withTestCases(Result.Failed, Result.Failed, Result.Failed)
                .withTestCases(Result.Failed)
                .whichHasAttachment()
                .whichHasAttachment()
                .done();

        return testRun;
    }

}
