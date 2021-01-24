package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlProcessorImpl implements XmlProcessor {

  public static final Logger LOG = Logger.getLogger(DbProcessorImpl.class.getName());

  public void writeXmlFromDb(Collection<Integer> collection) {
    final XMLOutputFactory factory = XMLOutputFactory.newFactory();
    try {
      XMLStreamWriter writer = factory.createXMLStreamWriter(
          new FileOutputStream(Constants.PATH_TO_XML_FROM_TEST_TABLE),"UTF-8");
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
      LOG.info("File write success");
    } catch (XMLStreamException | IOException e) {
      LOG.info("File write failed");
    }
  }

  public void XmlTransform(String pathToSourceXmlFile, String pathToXsltTemplateFile,
      String pathToTargetXmlFile) {
    File xsltTemplateFile = new File(pathToXsltTemplateFile);
//    File sourceXmlFile = new File(pathToSourceXmlFile);

    try {
//      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//      DocumentBuilder documentBuilder = factory.newDocumentBuilder();
//      Document documentFromXml = documentBuilder.parse(sourceXmlFile);
      TransformerFactory tFactory = TransformerFactory.newInstance();
//      StreamSource styleSource = new StreamSource(xsltTemplateFile);
      Transformer transformer = tFactory.newTransformer(new StreamSource(xsltTemplateFile));
//      transformer.setOutputProperty(OutputKeys.INDENT, "2");
//      transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
//      DOMSource source = new DOMSource(documentFromXml);
//      transformer.transform(source, new StreamResult(new FileOutputStream(pathToTargetXmlFile)));
      transformer.transform(new StreamSource(new FileInputStream(pathToSourceXmlFile))
          , new StreamResult(new FileOutputStream(pathToTargetXmlFile)));
      LOG.info("Transform xml success");
    } catch (IOException | TransformerException e) {
      LOG.info("Transform xml failed");
    }
  }

  public void parseValuesFromXml(String sequenceXmlFilePath) {
    Collection<Integer> parseValuesFromXml = new ArrayList<>();
    XMLInputFactory factory = XMLInputFactory.newInstance();

    try {
      XMLStreamReader parser = factory.createXMLStreamReader(
          new BufferedInputStream(new FileInputStream(sequenceXmlFilePath)),"UTF-8");
      while (parser.hasNext()) {
        if (parser.next() == 1 && parser.getLocalName().equals("entry")) {
          parseValuesFromXml.add(Integer.parseInt(parser.getAttributeValue(null, "field")));
        }
      }
    } catch (XMLStreamException | FileNotFoundException e) {
      LOG.info("Parse xml failed");
    }
    LOG.info(" Parse xml success");
    long sumResult = 0;
    for (int value : parseValuesFromXml) {
      sumResult = sumResult + value;
    }
    System.out.println("Summ of elements is " + sumResult);
  }
}
