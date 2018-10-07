package api.repository;

import api.model.models.TestRun;
import org.springframework.data.repository.CrudRepository;

public interface TestRunRepository extends CrudRepository<TestRun, Integer> {
}