package testreporter.core.services.parser;

import testreporter.core.services.deserializer.IXmlDeserializer;
import testreporter.core.models.TestRun;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

public class RawTestRunParser extends TestRunParser {

    public RawTestRunParser(IXmlDeserializer deserializer) {
        super(deserializer);
    }

    @Override
    public TestRun parseResult(InputStream resultStream) throws JAXBException {
        return super.deserialize(resultStream);
    }
}
