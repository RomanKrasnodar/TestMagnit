package application;

public final class Constants {

  public static final String PATH_TO_CONNECTION_PROPERTIES_FILE = "G:/GitHub/TestMagnit/src/main/resources/DbConnection.properties";
  public static final String PATH_TO_XML_FROM_TEST_TABLE = "1.xml";
  public static final String PATH_TO_XML_FROM_XSLT = "2.xml";
  public static final String PATH_TO_XSLT_FILE = "G:/GitHub/TestMagnit/src/main/resources/XSLTemplate.xsl";
  public static final String INPUT_START_MESSAGE = "Please enter the final number of the sequence between 1 and 2147483647";
  public static final String INPUT_EXCEPTION_MESSAGE = "Please enter the correct value.";
  public static final String DB_CREATE_QUERY = "CREATE TABLE test (field int)";
  public static final String DB_TRUNCATE_QUERY = "TRUNCATE TABLE test";
  public static final String DB_INSERT_VALUES_QUERY = "INSERT into test VALUES (?)";
  public static final String DB_SELECT_ALL_FIELDS_QUERY = "SELECT field FROM PUBLIC.TEST";
  public static final String NOT_FOUND_PROPERTIES_FILE_EXCEPTION_MESSAGE = "Create connection is failed, property file is not found";
  public static final String CONNECT_TO_DB_ERROR_MESSAGE = "Failed to connect to database,check the settings in property file";
  public static final String READING_PROPERTIES_FILE_ERROR_MESSAGE = "Error reading from property file";
  public static final String SUCCESS_ADD_DATA_IN_DB_MESSAGE = "Insert data in database success";
  public static final String FAILED_ADD_DATA_IN_DB_EXCEPTION_MESSAGE = "Insert data in database failed";
  public static final String CREATE_COLLECTION_FROM_DB_SUCCESS_MESSAGE = "Data successfully moved from database to collection";
  public static final String CREATE_COLLECTION_FROM_DB_ERROR_MESSAGE = "Failed to move data from database to collection";
  public static final String WRITE_XML_SUCCESS_MESSAGE = "Write xml write success";
  public static final String WRITE_XML_ERROR_MESSAGE = "Write xml write failed";
  public static final String TRANSFORM_XML_SUCCESS_MESSAGE = "Transform xml file success";
  public static final String TRANSFORM_XML_ERROR_MESSAGE = "Transform xml file failed";
  public static final String PARSE_XML_SUCCESS_MESSAGE = "Parse xml success";
  public static final String PARSE_XML_ERROR_MESSAGE = "Parse xml failed";

}