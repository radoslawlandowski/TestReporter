package testreporter.core.services.parser;

import testreporter.core.services.deserializer.IXmlDeserializer;
import testreporter.core.models.TestRun;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

public abstract class TestRunParser implements ITestRunParser {

    protected IXmlDeserializer deserializer;

    public TestRunParser(IXmlDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    protected TestRun deserialize(InputStream resultStream) throws JAXBException {
        return (TestRun)deserializer.deserialize(resultStream);
    }
}
