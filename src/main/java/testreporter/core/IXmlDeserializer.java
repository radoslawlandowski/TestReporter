package testreporter.core;

import javax.xml.bind.JAXBException;

public interface IXmlDeserializer<T> {
    T deserialize(String xmlContent) throws JAXBException;
}
