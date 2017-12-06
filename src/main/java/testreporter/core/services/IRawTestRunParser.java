package testreporter.core.services;

import testreporter.core.IXmlDeserializer;
import testreporter.core.TestRunDeserializer;
import testreporter.core.models.TestRun;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

public class IRawTestRunParser extends TestRunParser {

    public IRawTestRunParser(IXmlDeserializer deserializer) {
        super(deserializer);
    }

    @Override
    public TestRun parseResult(InputStream resultStream) throws JAXBException {
        return super.deserialize(resultStream);
    }
}
