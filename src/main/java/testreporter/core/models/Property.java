package testreporter.core.models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="property")
@Table(name = "Nunit_Property")
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    @Column(name = "name_name")
    String name;
    @Column(name = "prop_value")
    String value;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
