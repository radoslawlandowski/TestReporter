package testreporter.core.models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="failure")
@Table(name = "Failure")
@Entity
public class Failure {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    @Column
    String message;

    @Column(columnDefinition = "TEXT")
    String stackTrace;

    @XmlElement(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name = "stack-trace")
    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
