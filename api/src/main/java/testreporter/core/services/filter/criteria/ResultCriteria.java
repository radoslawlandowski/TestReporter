package testreporter.core.services.filter.criteria;

import testreporter.core.enums.Result;
import testreporter.core.models.TestCase;

import java.util.List;
import java.util.stream.Collectors;

public class ResultCriteria implements Criteria {

    Result result;

    public ResultCriteria(Result result) {
        this.result = result;
    }

    public List<TestCase> meetCriteria(List<TestCase> testCases) {
        return testCases.stream()
                .filter(testCase -> testCase.getResult().equals(result.getResult()))
                .collect(Collectors.toList());
    }
}
