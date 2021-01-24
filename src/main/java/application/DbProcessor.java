package application;

import java.sql.Connection;
import java.util.Collection;

public interface DbProcessor {

  Connection getConnectionWithProperties(String pathToPropertiesFile);

  void createTable(int maxValue);

  Collection<Integer> getCollectionFromDb();

}
