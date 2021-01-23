import java.util.Collection;

public class TestApplication {

  public static void main(String[] args) {
    int inputValue = InputParams.scanInputNumber();
    DbProcessor dbProcessor = new DbProcessor();
    dbProcessor.createTable(inputValue);
    Collection<Integer> collection = dbProcessor.getCollectionFromDb();
    WriterXml writerXml = new WriterXml();
    writerXml.write(collection);
  }
}
