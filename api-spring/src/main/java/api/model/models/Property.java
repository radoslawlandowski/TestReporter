package api.model.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="property")
@Table(name = "nunit_property")
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

    @JoinColumn(name = "resultfile_fk")
    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonIgnore
    File file;

    @Column(name = "resultfile_fk", insertable = false, updatable = false)
    Long resultFile_fk;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Long getResultFile_fk() {
        return resultFile_fk;
    }

    public void setResultFile_fk(Long resultFile_fk) {
        this.resultFile_fk = resultFile_fk;
    }

}
