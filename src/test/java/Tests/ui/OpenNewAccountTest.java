package Tests.ui;

import pages.LoginPage;
import pages.OpenNewAccountPage;
import Tests.reports.ReportFactory;
import utils.ConfigProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Tests.reports.ReportFactory.captureScreenshot;
import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OpenNewAccountTest {
    static ConfigProperties configProperties = new ConfigProperties();

    static String baseUrl = configProperties.getProperty("test.baseUrl");
    static String username = configProperties.getProperty("test.username");
    static String password = configProperties.getProperty("test.password");

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ExtentSparkReporter info = new ExtentSparkReporter("reports/ui/OpenNewAccountTest.html");
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
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.getUrl(baseUrl);
        loginPage.completeInputUsername(username);
        loginPage.completeInputPassword(password);
        loginPage.clickBtnLogIn();
    }


    @Test
    @Tag("OPEN_NEW_ACCOUNT_TYPE_SAVINGS")
    @Tag("SUCCESS_OPEN_NEW_ACCOUNT_TYPE_SAVINGS")
    public void successOpenNewAccount() throws InterruptedException {
        ExtentTest test = extent.createTest("Success Open New Account");

        test.log(Status.INFO, "Test Start");
        test.log(Status.INFO, "Navigate to the app - For open new account");

        OpenNewAccountPage openNewAccountPage = new OpenNewAccountPage(driver, wait);
        try {
            openNewAccountPage.clickLinkToGoNewOpenAccountPage();
            openNewAccountPage.selectTypeOfAccount("1");
            openNewAccountPage.clickBtnOpenNewAccount();

            test.log(Status.INFO, "Insert type of Account SAVINGS and send");

            String actualMessageOpenNewAccount = openNewAccountPage.getSuccessAccountOpenMessage();

            if (actualMessageOpenNewAccount.equals("Congratulations, your account is now open.")) {
                test.log(Status.PASS, "Verify 'Congratulations, your account is now open.' message is visible");
            } else {
                test.log(Status.FAIL, "FAIL VALIDATION: The successfully open new account message is not visible or not correct");
                captureScreenshot(test, "FAIL_REGISTER_MESSAGE_SUCCESS_REGISTER", driver);
            }


            assertEquals("The success message 'your account is now open' should be visible and correct", "Congratulations, your account is now open.", actualMessageOpenNewAccount);

        } catch (Exception e) {
            test.log(Status.FAIL, "FAIL TEST OPEN_NEW_ACCOUNT_TYPE_SAVINGS: " + e.getMessage());
            captureScreenshot(test, "FAIL_OPEN_NEW_ACCOUNT_TYPE_SAVINGS", driver);
            String actualMessageOpenNewAccount = openNewAccountPage.getSuccessAccountOpenMessage();
            assertEquals("The success message 'your account is now open' should be visible and correct", "Congratulations, your account is now open.", actualMessageOpenNewAccount);
        }
    }


    @AfterAll
    public void close() throws InterruptedException {
        driver.quit();
        extent.flush();
    }

}
