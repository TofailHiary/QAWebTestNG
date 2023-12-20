package com.DriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.Assert;

/**
 * The ReadProperty class is responsible for reading key-value pairs from a properties file.
 * This class is typically used to manage configuration data in a structured way.
 */
public class ReadProperty {

    public static String Par = "";

    /**
     * Reads a value associated with a given key from the 'config.properties' file.
     * <p>
     * This method opens the 'config.properties' file located in the 'src/test/resources' directory,
     * reads the value associated with the provided key, and returns it.
     * In case of any issues (like file not found or I/O errors), it prints the stack trace
     * and makes the test fail with an appropriate message.
     * </p>
     *
     * @param par The key for which the value needs to be fetched from the properties file.
     * @return The value corresponding to the provided key.
     */
    public static String ReadData(String par) {
        // Run in local
        File file = new File("src/test/resources/config.properties");
        // Run in export

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (Throwable e) {
            e.printStackTrace(System.out);
            Assert.fail("\nPlease check config file if exist\n");
        }
        Properties prop = new Properties();

        // load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            Assert.fail("\nPlease check config file Inputs\n");
        }

        return Par = prop.getProperty(par);
    }

}
