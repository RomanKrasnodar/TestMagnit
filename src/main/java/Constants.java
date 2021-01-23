import java.util.logging.Logger;

public final class Constants {

  public static final String PATH_TO_XML_FROM_TEST_TABLE = "1.xml";
  public static final String PATH_TO_XML_FROM_XSLT = "2.xml";
  public static final String INPUT_START_MESSAGE = "Please enter a positive number:";
  public static final String INPUT_EXCEPTION_MESSAGE = "The entered value must be greater than 0, please enter the correct value.";
  public static final String DB_URL = "jdbc:postgresql://localhost:5432/TestMagnit";
  public static final String DB_USER = "Roman";
  public static final String DB_PASSWORD = "1234";
  public static final String DB_CONNECTION_FAILED_MESSAGE = "Failed to connect to database.";
  public static final String DB_CREATE_QUERY = "CREATE TABLE test (field int)";
  public static final String DB_TRUNCATE_QUERY = "TRUNCATE TABLE test";
  public static final String DB_INSERT_VALUES_QUERY = "INSERT into test VALUES (?)";
  public static final String DB_SELECT_ALL_FIELDS_QUERY = "SELECT field FROM PUBLIC.TEST";
}
