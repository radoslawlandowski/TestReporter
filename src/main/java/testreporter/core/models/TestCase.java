package testreporter.core.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="test-case")
@Table(name = "testcase")
@Entity
public class TestCase {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int internalId;

    @Column
    int id;
    @Column
    String name;
    @Column
    String fullname;
    @Column
    String result;
    @Column
    String time;
    @Column
    int asserts;

    @OneToMany(targetEntity = Property.class, cascade = CascadeType.ALL)
    List<Property> properties;

    @OneToOne(targetEntity = Failure.class, cascade = CascadeType.ALL)
    Failure failure;

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
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
}
