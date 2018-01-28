package testreporter.core.services.filter.criteria;

import testreporter.core.models.TestCase;

import java.util.List;

public interface Criteria {
    List<TestCase> meetCriteria(List<TestCase> testCases);
}
