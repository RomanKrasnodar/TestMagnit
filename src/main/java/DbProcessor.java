import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

public class DbProcessor {


  public static final Logger LOG = Logger.getLogger(DbProcessor.class.getName());


  public void createTable(int maxValue) {
    try (Connection dBConnection = DriverManager
        .getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
        Statement statement = dBConnection.createStatement()) {
      try {
        statement.execute(Constants.DB_CREATE_QUERY);
        LOG.info("Table is created");
      } catch (PSQLException e) {
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
    try (Connection dBConnection = DriverManager
        .getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
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
      return new ArrayList<Integer>();
    }
  }
}

