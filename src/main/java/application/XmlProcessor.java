package application;

import java.util.Collection;

public interface XmlProcessor {

  void writeXmlFromDb(Collection<Integer> collection);

  void XmlTransform(String pathToSourceXmlFile,
      String pathToXsltTemplateFile,
      String pathToTargetXmlFile);

  void parseValuesFromXml(String sequenceXmlFilePath);

}
