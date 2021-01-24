package application;

import java.util.Collection;

public class TestApplication {

  public static void main(String[] args) {
    int inputValue = ConsoleValueScanner.scanInputNumber();
    DbProcessor dbProcessorImpl = new DbProcessorImpl();
    dbProcessorImpl.createTable(inputValue);
    Collection<Integer> collection = dbProcessorImpl.getCollectionFromDb();
    XmlProcessor xmlProcessorImpl = new XmlProcessorImpl();
    xmlProcessorImpl.writeXmlFromDb(collection);
    xmlProcessorImpl
        .XmlTransform(
            Constants.PATH_TO_XML_FROM_TEST_TABLE,
            Constants.PATH_TO_XSLT_FILE,
            Constants.PATH_TO_XML_FROM_XSLT);
    xmlProcessorImpl.parseValuesFromXml(Constants.PATH_TO_XML_FROM_XSLT);
  }
}
