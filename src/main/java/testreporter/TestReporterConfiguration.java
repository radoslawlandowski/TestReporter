package testreporter;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class TestReporterConfiguration extends Configuration {

    @NotEmpty
    private String resultsFolder;

    @JsonProperty
    public String getResultsFolder() {
        return resultsFolder;
    }

    @JsonProperty
    public void setResultsFolder(String resultsFolder) {
        this.resultsFolder = resultsFolder;
    }
}
