package api.services.filter.criteria;

import api.model.models.TestCase;

import java.util.List;

public interface Criteria {
    List<TestCase> meetCriteria(List<TestCase> testCases);
}
