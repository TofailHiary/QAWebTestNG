package com.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.DriverManager.SetupDriver;

/**
 * Helper class extends SetupDriver to provide additional utilities for
 * Selenium WebDriver interactions, such as waiting for elements or conditions.
 */
public class Helper extends SetupDriver {

    /**
     * Constructs a Helper instance.
     */
    public Helper() {
    
    }

    /**
     * Waits until the specified element is clickable.
     *
     * @param element the WebElement to wait for
     */
    public void waitUntilElementIsClickable(WebElement element) {
        getWaitInstance().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the specified element is visible.
     *
     * @param element the WebElement to wait for
     */
    public void waitUntilElementIsVisible(WebElement element) {
        getWaitInstance().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the web page is completely loaded.
     */
    public void waitUntilPageIsLoaded() {
        getWaitInstance().until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Waits until the specified element is no longer visible.
     *
     * @param element the WebElement to wait for
     */
    public void waitUntilElementIsInvisible(WebElement element) {
        getWaitInstance().until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits for a specified number of seconds.
     * 
     * @param seconds the number of seconds to wait
     */
    public void waitFor(int seconds) {
        getWaitInstance().until(webDriver -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
                return true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        });
    }

    /**
     * Checks if the specified element is present on the page.
     *
     * @param element the WebElement to check
     * @return true if the element is present and visible, false otherwise
     */
    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
