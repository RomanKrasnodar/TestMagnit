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
import org.postgresql.Driver;

public class DbProcessorImpl implements DbProcessor {

  public static final Logger LOG = Logger.getLogger(DbProcessorImpl.class.getName());

  public Connection getConnectionWithProperties(String pathToPropertiesFile) {
    Connection connection = null;
    File propertyFile = new File(pathToPropertiesFile);
    try (InputStream inputStreamFromPropertyFile = new FileInputStream(propertyFile)) {
      Properties properties = new Properties();
      properties.load(inputStreamFromPropertyFile);
      Driver driver = new org.postgresql.Driver();
      DriverManager.registerDriver(driver);
      connection = DriverManager.getConnection(
          properties.getProperty("jdbc.url"),
          properties.getProperty("jdbc.user"),
          properties.getProperty("jdbc.password"));
    } catch (FileNotFoundException e) {
      LOG.info(Constants.NOT_FOUND_PROPERTIES_FILE_EXCEPTION_MESSAGE);
    } catch (SQLException e) {
      LOG.info(Constants.CONNECT_TO_DB_ERROR_MESSAGE);
    } catch (IOException e) {
      LOG.info(Constants.READING_PROPERTIES_FILE_ERROR_MESSAGE);
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
        LOG.info(Constants.SUCCESS_ADD_DATA_IN_DB_MESSAGE);
      }
    } catch (SQLException throwables) {
      LOG.info(Constants.FAILED_ADD_DATA_IN_DB_EXCEPTION_MESSAGE);
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
        LOG.info(Constants.CREATE_COLLECTION_FROM_DB_SUCCESS_MESSAGE);
        return collection;
      }
    } catch (SQLException e) {
      LOG.info(Constants.CREATE_COLLECTION_FROM_DB_ERROR_MESSAGE);
      return Collections.emptyList();
    }
  }
}

