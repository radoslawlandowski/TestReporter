package testreporter.core.models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="test-suite")
@Entity
@Table(name = "TestSuite")
public class TestSuite implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int internalId;
    @Column
    String type;
    @Column
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

    @OneToMany(targetEntity = Property.class, cascade = CascadeType.ALL)
    List<Property> properties;

    @OneToOne(targetEntity = Failure.class, cascade = CascadeType.ALL)
    Failure failure;

    @OneToMany(targetEntity = TestCase.class, cascade = CascadeType.ALL)
    List<TestCase> testCases;

    @OneToMany(targetEntity = TestSuite.class, cascade = CascadeType.ALL)
    List<TestSuite> testSuites;

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "id")
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

    @XmlElementWrapper(name = "properties")
    @XmlElement(name = "property", type = Property.class)
    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @XmlElement(name = "failure")
    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    @XmlElement(name = "test-case")
    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    @XmlElement(name = "test-suite")
    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }
}
