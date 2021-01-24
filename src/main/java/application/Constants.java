package application;

import java.util.logging.Logger;

public final class Constants {

  public static final String PATH_TO_CONNECTION_PROPERTIES_FILE = "DbConnection.properties";
  public static final String PATH_TO_XML_FROM_TEST_TABLE = "1.xml";
  public static final String PATH_TO_XML_FROM_XSLT = "2.xml";
  public static final String PATH_TO_XSLT_FILE = "Transformer.xsl";
  public static final String INPUT_START_MESSAGE = "Please enter the final number of the sequence between 1 and 2147483647";
  public static final String INPUT_EXCEPTION_MESSAGE = "Please enter the correct value.";
  public static final String DB_CREATE_QUERY = "CREATE TABLE test (field int)";
  public static final String DB_TRUNCATE_QUERY = "TRUNCATE TABLE test";
  public static final String DB_INSERT_VALUES_QUERY = "INSERT into test VALUES (?)";
  public static final String DB_SELECT_ALL_FIELDS_QUERY = "SELECT field FROM PUBLIC.TEST";
}
