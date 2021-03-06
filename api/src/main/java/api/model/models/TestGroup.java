package api.model.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "test_group")
@NamedQueries({
    @NamedQuery(
        name = "TestGroup.findByTestGroupName",
        query = "from TestGroup tg where tg.name = :testGroupName"
    ),
    @NamedQuery(
        name = "TestGroup.findAll",
        query = "from TestGroup"
    )
})
@Entity
public class TestGroup implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @Column(unique = true)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column
    @OneToMany(targetEntity = TestRun.class, mappedBy = "testGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<TestRun> testRuns = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestRun> getTestRuns() {
        return testRuns;
    }

    public void setTestRuns(List<TestRun> testRuns) {
        this.testRuns = testRuns;
    }
}
