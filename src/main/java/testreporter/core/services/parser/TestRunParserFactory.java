package testreporter.core.services.parser;

import testreporter.core.services.deserializer.IXmlDeserializer;
import testreporter.core.enums.TestRunParserTypes;
import testreporter.core.services.unzipper.IFileUnzipper;

public class TestRunParserFactory {

    protected IXmlDeserializer deserializer;
    protected IFileUnzipper fileUnzipper;

    public TestRunParserFactory(IXmlDeserializer deserializer, IFileUnzipper fileUnzipper) {
        this.deserializer = deserializer;
        this.fileUnzipper = fileUnzipper;
    }

    public ITestRunParser create(TestRunParserTypes type) {
        ITestRunParser parser = null;

        switch(type) {
            case RAW:
                parser = new RawTestRunParser(this.deserializer);
                break;
            case ZIPPED:
                parser = new ZippedTestRunParser(this.deserializer, this.fileUnzipper);
                break;
        }

        return parser;
    }
}
