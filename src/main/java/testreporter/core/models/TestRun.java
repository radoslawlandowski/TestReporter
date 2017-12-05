package testreporter.core.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "TestRun.findAll",
                query = "from TestRun"
        ),
        @NamedQuery(
        name = "TestRun.findByGroupName",
        query = "from TestRun tr where tr.testGroup.name = :testGroupName"
    )
})
@XmlRootElement(name="test-run")
@Table(name = "TestRun")
public class TestRun implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @Column
    String name;
    @Column
    String fullname;
    @Column
    String testcasecount;
    @Column
    String result;
    @Column
    String time;
    @Column
    int total;
    @Column
    int passed;
    @Column
    int failed;
    @Column
    int inconclusive;
    @Column
    int skipped;
    @Column
    int asserts;
    @Column
    String runDate;
    @Column
    String startTime;

    @ManyToOne(targetEntity = TestGroup.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    TestGroup testGroup;

    @OneToOne(targetEntity=TestSuite.class, cascade = CascadeType.ALL)
    TestSuite testSuite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @XmlAttribute(name = "testcasecount")
    public String getTestcasecount() {
        return testcasecount;
    }

    public void setTestcasecount(String testcasecount) {
        this.testcasecount = testcasecount;
    }

    @XmlAttribute(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @XmlAttribute(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @XmlAttribute(name = "total")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @XmlAttribute(name = "passed")
    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    @XmlAttribute(name = "failed")
    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    @XmlAttribute(name = "inconclusive")
    public int getInconclusive() {
        return inconclusive;
    }

    public void setInconclusive(int inconclusive) {
        this.inconclusive = inconclusive;
    }

    @XmlAttribute(name = "skipped")
    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    @XmlAttribute(name = "asserts")
    public int getAsserts() {
        return asserts;
    }

    public void setAsserts(int asserts) {
        this.asserts = asserts;
    }

    @XmlAttribute(name = "run-date")
    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    @XmlAttribute(name = "start-time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @XmlElement(name = "test-suite")
    public TestSuite getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuite testSuite) {
        this.testSuite = testSuite;
    }

    public TestGroup getTestGroup() {
        return testGroup;
    }

    public void setTestGroup(TestGroup testGroup) {
        this.testGroup = testGroup;
    }

}
