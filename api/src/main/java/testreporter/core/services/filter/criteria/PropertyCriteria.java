package testreporter.core.services.filter.criteria;

import testreporter.core.models.TestCase;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PropertyCriteria implements Criteria {
    @Override
    public List<TestCase> meetCriteria(List<TestCase> testCases) {
        return testCases.stream()
                .filter(Objects::nonNull)
                .filter(testCase -> ( (testCase.getProperties()) != null) && !testCase.getProperties().isEmpty())
                .collect(Collectors.toList());
    }
}
