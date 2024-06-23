package Tests.ui;

import pages.RegisterPage;
import Tests.reports.ReportFactory;
import utils.ConfigProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Tests.reports.ReportFactory.captureScreenshot;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisterTest {
	static ConfigProperties configProperties = new ConfigProperties();

	static String baseUrl = configProperties.getProperty("test.baseUrl");

	static String username = configProperties.getProperty("test.username");

	static String password = configProperties.getProperty("test.password");

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static ExtentSparkReporter info = new ExtentSparkReporter("reports/ui/RegisterTest.html");
	private static ExtentReports extent;

	@BeforeAll
	public static void createReport() {
		extent = ReportFactory.getInstance();
		extent.attachReporter(info);
	}

	@BeforeAll
	public static void setUp() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		RegisterPage registerPage = new RegisterPage(driver, wait);
		registerPage.getUrl(baseUrl);
	}

	@Test
	@Tag("REGISTER")
	@Tag("SUCCESS_REGISTER")
	public void successRegister() throws InterruptedException {
		ExtentTest test = extent.createTest("Success Register");

		test.log(Status.INFO, "Test Start");
		test.log(Status.INFO, "Navigate to the app - for register user");

		RegisterPage registerPage = new RegisterPage(driver, wait);
		try {
			registerPage.clickLinkToGoRegisterForm();

			registerPage.completeInputFirstName("Mauro");
			registerPage.completeInputLastName("Mas");
			registerPage.completeInputAddress("address");
			registerPage.completeInputCity("city");
			registerPage.completeInputState("state");
			registerPage.completeInputZipCode("zipcode");
			registerPage.completeInputPhone("0991990009");
			registerPage.completeInputSSN("ssn");
			registerPage.completeInputUsername(username);
			registerPage.completeInputPassword(password);
			registerPage.completeInputPasswordConfirm("123456");
			registerPage.clickRegisterBtn();

			test.log(Status.INFO, "Insert all the data on Form and send");

			String actualMessageAccountCreated = registerPage.getMessageAccountCreated();

			if ( actualMessageAccountCreated.equals("Your account was created successfully. You are now logged in.") ) {
				test.log(Status.PASS, "Verify 'Your Account was created successfully. You are now logged in.' Message about success register is visible");
			} else {
				test.log(Status.FAIL, "FAIL VALIDATION: The Account Successfully Message about success register is not visible or not correct");
				captureScreenshot(test, "FAIL_REGISTER_MESSAGE_SUCCESS_REGISTER", driver);
			}


			assertEquals("The success message Register should be visible and correct", "Your account was created successfully. You are now logged in.", actualMessageAccountCreated);

		} catch (Exception e) {
			test.log(Status.FAIL, "FAIL TEST REGISTER: " + e.getMessage());
			captureScreenshot(test, "FAIL_REGISTER", driver);
			String actualMessageAccountCreated = registerPage.getMessageAccountCreated();
			assertEquals("The success message Register should be visible and correct", "Your account was created successfully. You are now logged in.", actualMessageAccountCreated);
		}
	}


	@AfterAll
	public void close() throws InterruptedException {
		driver.quit();
		extent.flush();
	}

}
