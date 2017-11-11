package testreporter.core;

import testreporter.core.models.TestRun;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class TestRunDeserializer implements IXmlDeserializer<TestRun> {

    public TestRun deserialize(String xmlContent) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TestRun.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xmlContent);

        TestRun testRun = (TestRun)jaxbUnmarshaller.unmarshal(reader);

        return testRun;
    }
}
