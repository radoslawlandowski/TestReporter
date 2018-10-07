package api.repository;

import api.model.models.TestGroup;
import org.springframework.data.repository.CrudRepository;

public interface TestGroupRepository extends CrudRepository<TestGroup, Integer> {
    public TestGroup findByName(String name);
}