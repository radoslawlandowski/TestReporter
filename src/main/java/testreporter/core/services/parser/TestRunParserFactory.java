package testreporter.core.services.parser;

import testreporter.core.services.deserializer.IXmlDeserializer;
import testreporter.core.enums.ResultFileTypes;
import testreporter.core.services.unzipper.IFileUnzipper;
import testreporter.core.services.validator.FileUploadValidator;

public class TestRunParserFactory {

    protected IXmlDeserializer deserializer;
    protected IFileUnzipper fileUnzipper;

    public TestRunParserFactory(IXmlDeserializer deserializer, IFileUnzipper fileUnzipper) {
        this.deserializer = deserializer;
        this.fileUnzipper = fileUnzipper;
    }

    public ITestRunParser create(ResultFileTypes type) {
        ITestRunParser parser = null;

        switch(type) {
            case RAW:
                parser = new RawTestRunParser(this.deserializer);
                break;
            case ZIPPED:
                parser = new ZippedTestRunParser(this.deserializer, this.fileUnzipper, new FileUploadValidator());
                break;
        }

        return parser;
    }
}
