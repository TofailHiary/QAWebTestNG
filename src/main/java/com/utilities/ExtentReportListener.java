package com.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.DriverManager.SetupDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ExtentReportListener implements ITestListener {
	private static ExtentReports extent = ExtentManager.getReporter();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite is ending!");
		extent.flush(); // Write test information to report
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getDescription(); // Get the description
		System.out.println("Description: " + testName); // Debug statement

		if (testName == null || testName.isEmpty()) {
			testName = result.getMethod().getMethodName();
		}

		System.out.println("Test being started: " + testName); // Debug statement
		test.set(extent.createTest(testName));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " passed!");
		test.get().pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " failed!");

		// Get the driver instance from the test class
		Object testClass = result.getInstance();
		WebDriver driver = ((SetupDriver) testClass).setupBrowser();

		// Capture the screenshot
		String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());

		// Attach the screenshot to the ExtentReport
		if (screenshotPath != null) {
			test.get().fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else {
			test.get().fail(result.getThrowable());
		}
	}

	private String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = "screenshots/" + screenshotName + System.currentTimeMillis() + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " skipped!");
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
	}
}
