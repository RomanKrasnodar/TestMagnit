package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

public class DbProcessorImpl implements DbProcessor{

  public static final Logger LOG = Logger.getLogger(DbProcessorImpl.class.getName());

  public Connection getConnectionWithProperties(String pathToPropertiesFile) {
    Connection connection = null;
    File propertyFile = new File(pathToPropertiesFile);
    try (InputStream inputStreamFromPropertyFile = new FileInputStream(propertyFile)) {
      Properties properties = new Properties();
      properties.load(inputStreamFromPropertyFile);
      connection = DriverManager.getConnection(
          properties.getProperty("jdbc.url"),
          properties.getProperty("jdbc.user"),
          properties.getProperty("jdbc.password"));
    } catch (FileNotFoundException e) {
      LOG.info("Property file is not found");
    } catch (SQLException throwables) {
      LOG.info("Failed to connect to database,check the settings in property file");
    } catch (IOException e) {
      LOG.info("Error reading from property file");
    }

    return connection;
  }

  public void createTable(int maxValue) {
    try (Connection dBConnection = getConnectionWithProperties(
        Constants.PATH_TO_CONNECTION_PROPERTIES_FILE);
        Statement statement = dBConnection.createStatement()) {
      try {
        statement.execute(Constants.DB_CREATE_QUERY);
        LOG.info("Table is created");
      } catch (SQLException e) {
        statement.execute(Constants.DB_TRUNCATE_QUERY);
        LOG.info("Table is truncated");
      }

      try (PreparedStatement preparedStatement =
          dBConnection.prepareStatement(Constants.DB_INSERT_VALUES_QUERY)) {
        for (int i = 1; i < maxValue + 1; i++) {
          preparedStatement.setInt(1, i);
          preparedStatement.addBatch();
          if (i % 10000 == 0) {
            preparedStatement.executeBatch();
          }
        }
        preparedStatement.executeBatch();
        LOG.info("Insert data success");
      }
    } catch (SQLException throwables) {
      LOG.info("Insert data failed");
    }
  }

  public Collection<Integer> getCollectionFromDb() {
    try (Connection dBConnection = getConnectionWithProperties(
        Constants.PATH_TO_CONNECTION_PROPERTIES_FILE);
        PreparedStatement preparedStatement = dBConnection
            .prepareStatement(Constants.DB_SELECT_ALL_FIELDS_QUERY)) {
      try (ResultSet setFromDb = preparedStatement.executeQuery()) {
        Collection<Integer> collection = new ArrayList<>();
        while (setFromDb.next()) {
          collection.add(setFromDb.getInt(1));
        }
        LOG.info("Data successfully moved from database to collection");
        return collection;
      }
    } catch (SQLException e) {
      LOG.info("Failed to move data from database to collection");
      return Collections.emptyList();
    }
  }
}

