package api.services.deserializer;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

public interface IXmlDeserializer<T> {
    T deserialize(InputStream is) throws JAXBException;
}
