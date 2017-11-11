package testreporter.core;

import testreporter.core.models.TestRun;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

public class TestRunDeserializer implements IXmlDeserializer<TestRun> {

    public TestRun deserialize(InputStream is) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TestRun.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        TestRun testRun = (TestRun)jaxbUnmarshaller.unmarshal(is);

        return testRun;
    }
}
