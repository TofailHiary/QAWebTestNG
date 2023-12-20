package testData;

/**
 * The Constants class holds various constant values used across the application.
 * This includes messages for validation, file paths, and filenames.
 * It's a utility class to centralize commonly used constants for easy maintenance and readability.
 */
public class Constants {

    /**
     * Message displayed when the user inputs invalid credentials.
     */
    public static final String wrong_creds = "Invalid credentials"; 

    /**
     * Message displayed when mandatory fields are left empty.
     */
    public static final String Empty_fields= "Required";

    /**
     * The file path to the directory where test data Excel files are stored.
     * This path is dynamically constructed using the user's current working directory.
     */
    public static final String Path_TestData = System.getProperty("user.dir")+"\\src\\test\\resources\\excelExportAndFileIO\\";

    /**
     * The filename of the Excel file used for storing test case results.
     */
    public static final String File_TestData = "TestCasesResultFinal.xlsx";
}
