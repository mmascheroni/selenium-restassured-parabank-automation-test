package Tests.ui;

import pages.AccountsOverviewPage;
import pages.LoginPage;
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
import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountsOverviewTest {
    static ConfigProperties configProperties = new ConfigProperties();

    static String baseUrl = configProperties.getProperty("test.baseUrl");

    static String username = configProperties.getProperty("test.username");

    static String password = configProperties.getProperty("test.password");

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ExtentSparkReporter info = new ExtentSparkReporter("reports/ui/AccountsOverviewTest.html");
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
    @Tag("ACCOUNTS_OVERVIEW")
    @Tag("SUCCESS_ACCOUNTS_OVERVIEW")
    public void successAccountsOverview() throws InterruptedException {
        ExtentTest test = extent.createTest("Success Accounts Overview");

        test.log(Status.INFO, "Test Start");
        test.log(Status.INFO, "Navigate to the app - to accounts overview");

        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver, wait);
        try {
            accountsOverviewPage.clickLinkToGoAccountsOverviewPage();

            String actualMessage = accountsOverviewPage.getTextToValidate();

            if (actualMessage.equals("*Balance includes deposits that may be subject to holds")) {
                test.log(Status.PASS, "Verify '*Balance includes deposits that may be subject to holds' text is visible");
            } else {
                test.log(Status.FAIL, "FAIL VALIDATION: The text to validate is not visible or not correct");
                captureScreenshot(test, "FAIL_ACCOUNTS_OVERVIEW", driver);
            }


            assertEquals("The text to validate '*Balance includes deposits that may be subject to holds' should be visible and correct", "*Balance includes deposits that may be subject to holds", actualMessage);

        } catch (Exception e) {
            test.log(Status.FAIL, "FAIL TEST FAIL_ACCOUNTS_OVERVIEW: " + e.getMessage());
            captureScreenshot(test, "FAIL_ACCOUNTS_OVERVIEW", driver);
            String actualMessage = accountsOverviewPage.getTextToValidate();
            assertEquals("The success message 'your account is now open' should be visible and correct", "Congratulations, your account is now open.", actualMessage);
        }
    }

    @Test
    @Tag("ACCOUNTS_OVERVIEW_ALL_MONTHS")
    @Tag("SUCCESS_ACCOUNTS_OVERVIEW")
    public void successAccountsOverviewAllMonths() throws InterruptedException {
        ExtentTest test = extent.createTest("Success Accounts Overview all months");

        test.log(Status.INFO, "Test Start");
        test.log(Status.INFO, "Navigate to the app - to accounts overview all months");

        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver, wait);
        try {
            accountsOverviewPage.clickLinkToGoAccountsOverviewPage();

            String actualMessage = accountsOverviewPage.getTextToValidate();

            if (actualMessage.equals("*Balance includes deposits that may be subject to holds")) {
                test.log(Status.PASS, "Verify '*Balance includes deposits that may be subject to holds' text is visible");
            } else {
                test.log(Status.FAIL, "FAIL VALIDATION: The text to validate is not visible or not correct");
                captureScreenshot(test, "FAIL_ACCOUNTS_OVERVIEW_ALL_MONTHS", driver);
            }


            assertEquals("The text to validate '*Balance includes deposits that may be subject to holds' should be visible and correct", "*Balance includes deposits that may be subject to holds", actualMessage);

            accountsOverviewPage.clickFirstAccount();

            String accountDetailsMessage = accountsOverviewPage.getTextAccountDetailsToValidate();

            if (accountDetailsMessage.equals("Account Details")) {
                test.log(Status.PASS, "Verify 'Account Details' text is visible");
            } else {
                test.log(Status.FAIL, "FAIL VALIDATION: The text to validate is not visible or not correct");
                captureScreenshot(test, "FAIL_ACCOUNTS_OVERVIEW_ALL_MONTHS", driver);
            }

            assertEquals("The success message 'Account Details' should be visible and correct", "Account Details", accountDetailsMessage);

            accountsOverviewPage.selectActivityPeriod("All");
            accountsOverviewPage.selectType("All");
            accountsOverviewPage.clickBtnGo();

            boolean tableTransactionIsPresent = accountsOverviewPage.isTableVisible();
            boolean pNoTransactionIsPresent = accountsOverviewPage.isPNoTransactionVisible();


            if ( tableTransactionIsPresent ) {
                test.log(Status.PASS, "Verify the table transactions is present so the account have transactions");
                assertTrue("Verify the table transactions is present so the account have transactions", tableTransactionIsPresent);
                assertFalse("Verify the table transactions is present so the account have transactions and p no transaction found is not displayed", pNoTransactionIsPresent);

            } else {
                test.log(Status.PASS, "Verify the table transactions is not present so the account have not transactions");
                assertFalse("Verify the table transactions is not present", tableTransactionIsPresent);
                assertTrue("Verify the table transactions is not present and p no transaction found is displayed", pNoTransactionIsPresent);
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "FAIL TEST FAIL_ACCOUNTS_OVERVIEW_ALL_MONTHS: " + e.getMessage());
            captureScreenshot(test, "FAIL_ACCOUNTS_OVERVIEW_ALL_MONTHS", driver);
            String accountDetailsMessage = accountsOverviewPage.getTextAccountDetailsToValidate();
            assertEquals("The success message 'Account Details' should be visible and correct", "Account Details", accountDetailsMessage);
        }
    }



    @AfterAll
    public void close() throws InterruptedException {
        driver.quit();
        extent.flush();
    }

}
