package com.DriverManager;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * The SetupDriver class extends the ReadProperty class and is responsible for setting up and tearing down the WebDriver.
 * This class is typically used in test automation for initializing and finalizing the browser state for each test method.
 */
public class SetupDriver extends ReadProperty {

    protected WebDriver driver;
    public static WebDriverWait wait;
    protected static final int DEFAULT_TIMEOUT = 30;

    /**
     * Sets up the browser for the test execution.
     * <p>
     * This method initializes the ChromeDriver, maximizes the window, deletes all cookies, and navigates to a URL 
     * specified in the properties file. It also initializes an instance of WebDriverWait and sets an implicit wait.
     * </p>
     * @return An instance of the WebDriver.
     */
    @BeforeMethod(alwaysRun = true)
    public WebDriver setupBrowser() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            String url = ReadProperty.ReadData("Site_URL");
            driver.get(url);
        }
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver + " has been opened");
        return driver;
    }

    /**
     * Tears down the browser setup after test execution.
     * <p>
     * This method closes and quits the WebDriver instance and resets it to null, ensuring that the browser is properly 
     * shut down after each test method execution.
     * </p>
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

    /**
     * Provides access to the WebDriverWait instance.
     * @return The WebDriverWait instance currently in use.
     */
    public static WebDriverWait getWaitInstance() {
        return wait;
    }
}
