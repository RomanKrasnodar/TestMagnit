import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class WriterXml {

  public static final Logger LOG = Logger.getLogger(DbProcessor.class.getName());

  public void write(Collection<Integer> collection) {
    final XMLOutputFactory factory = XMLOutputFactory.newFactory();
    try {
      XMLStreamWriter writer = factory.createXMLStreamWriter(
          new FileOutputStream(Constants.PATH_TO_XML_FROM_TEST_TABLE), "UTF-8");
      writer.writeStartElement("entries");
      writer.writeCharacters("\n");
      for (Integer aDbQueryResult : collection) {
        writer.writeStartElement("entry");
        writer.writeCharacters("\n");
        writer.writeStartElement("field");
        writer.writeCharacters(String.valueOf(aDbQueryResult));
        writer.writeEndElement();
        writer.writeCharacters("\n");
        writer.writeEndElement();
        writer.writeCharacters("\n");
      }
      writer.writeEndElement();
      writer.writeEndDocument();
      writer.close();
      LOG.info("File write sucess");
    } catch (XMLStreamException | IOException e) {
      LOG.info("File write failed");
    }
  }
}
