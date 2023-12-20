
package loginpage;

import locators.LoginLocators;
import testData.Constants;
import testData.UsersManager;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.DriverManager.SetupDriver;
import com.utilities.*;

import java.text.SimpleDateFormat;

public class LoginAsAdministrator extends SetupDriver {
	Helper helper = new Helper();
	UsersManager userValid = UsersManager.getUserObject("userValid");
	UsersManager userInvalid = UsersManager.getUserObject("userinvalid");
	public static SimpleDateFormat TF = new SimpleDateFormat("HH:mm:ss");

	@BeforeClass
	public static void setUpClass() throws Exception {
		ReadWriteTestCases.setExcelFile(Constants.Path_TestData + Constants.File_TestData, "Sheet4");
		ReadWriteTestCases.writeHeaders();
	}

	@BeforeClass
	public void setUpUsers() {
		UsersManager.loadUsers();
	}

	@Test
	public void LoginAsAdministrator() throws Exception {
		executeTest("LoginAsAdministratorWithInvalidUser", () -> {

			LoginLocators loginPageEle = new LoginLocators(setupBrowser());
			System.out.println("Start: " + TF.format(System.currentTimeMillis()));

			loginPageEle.getUsernameField().sendKeys(userInvalid.username);
			loginPageEle.getPasswordField().sendKeys(userInvalid.password);

			loginPageEle.getLoginButton().click();

			helper.waitUntilElementIsVisible(loginPageEle.getWorngcreds());

			try {

				String actualMessage = loginPageEle.getWorngcreds().getText();
				Assert.assertEquals(actualMessage, Constants.wrong_creds);

			} catch (AssertionError e) {

				throw e;
			}

			System.out.println("Finish: " + TF.format(System.currentTimeMillis()));

		});
	}

	@Test
	public void LoginAsAdministratorIncorrectPassword() throws Exception {
		executeTest("LoginAsAdministratorWithValidUser", () -> {
			LoginLocators loginPageEle = new LoginLocators(setupBrowser());

			System.out.println("Start: " + TF.format(System.currentTimeMillis()));

			loginPageEle.getUsernameField().sendKeys(userValid.username);

			loginPageEle.getPasswordField().sendKeys(userValid.password);
			loginPageEle.getLoginButton().click();
			helper.waitUntilElementIsVisible(loginPageEle.getDahsboard());

	
			try {

				Assert.assertTrue(loginPageEle.getDahsboard().isDisplayed());

			} catch (AssertionError e) {
	

				throw e; // Re-throw the error to fail the test
			}

			System.out.println("Finish: " + TF.format(System.currentTimeMillis()));
		});
	}

	@Test
	public void LoginAsAdministratorEmptyField() throws Exception {
		executeTest("LoginAsAdministratorWithEmptyfield", () -> {
			LoginLocators loginPageEle = new LoginLocators(setupBrowser());

			System.out.println("Start: " + TF.format(System.currentTimeMillis()));

			loginPageEle.getLoginButton().click();
			helper.waitUntilElementIsVisible(loginPageEle.getRequiredMessage());

			try {

				Assert.assertEquals(loginPageEle.getRequiredMessage().getText(), Constants.Empty_fields);

			} catch (AssertionError e) {

				throw e; // Re-throw the error to fail the test
			}

			System.out.println("Finish: " + TF.format(System.currentTimeMillis()));
		});
	}

	private void executeTest(String testName, Runnable testLogic) throws Exception {
		String testCaseId = ReadWriteTestCases.generateTestCaseId();
		try {
			testLogic.run();
			ReadWriteTestCases.writeTestResult(testCaseId, testName, "Passed");
		} catch (AssertionError e) {
			ReadWriteTestCases.writeTestResult(testCaseId, testName, "Failed");
			throw e; // Re-throw the error to fail the test
		}
	}
}
