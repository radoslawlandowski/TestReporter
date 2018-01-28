package testreporter.core.models;

import java.util.List;
import java.util.Optional;

public class TestResults {
    protected File xmlFile;
    protected List<File> attachments;

    public TestResults(File xmlFile, List<File> attachments) {
        this.xmlFile = xmlFile;
        this.attachments = attachments;
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public Optional<List<File>> getAttachments() {
        return Optional.ofNullable(attachments);
    }

}
