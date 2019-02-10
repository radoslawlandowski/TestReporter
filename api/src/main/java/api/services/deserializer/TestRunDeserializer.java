package api.services.deserializer;

import api.model.models.TestRun;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class TestRunDeserializer implements IXmlDeserializer<TestRun> {

    public TestRun deserialize(byte[] data) throws JAXBException {
        return deserialize(new ByteArrayInputStream(data));
    }

    public TestRun deserialize(InputStream is) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TestRun.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        TestRun testRun = (TestRun)jaxbUnmarshaller.unmarshal(is);

        return testRun;
    }
}
