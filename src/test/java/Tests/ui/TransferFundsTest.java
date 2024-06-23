package Tests.ui;

import pages.LoginPage;
import pages.TransferFundsPage;
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
public class TransferFundsTest {

    static ConfigProperties configProperties = new ConfigProperties();

    static String baseUrl = configProperties.getProperty("test.baseUrl");
    static String username = configProperties.getProperty("test.username");
    static String password = configProperties.getProperty("test.password");

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ExtentSparkReporter info = new ExtentSparkReporter("reports/ui/TransferFundsTest.html");
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
    @Tag("TRANSFER_FUNDS")
    @Tag("SUCCESS_TRANSFER_FUNDS")
    public void successTransferFunds() throws InterruptedException {
        ExtentTest test = extent.createTest("Success Transfer Funds");

        test.log(Status.INFO, "Test Start");
        test.log(Status.INFO, "Navigate to the app - For transfer funds");

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        try {
            transferFundsPage.clickLinkToGoTransferFundsPage();
            transferFundsPage.sendAmount("123");
            transferFundsPage.selectFromAccount(1);
            transferFundsPage.selectToAccount(2);
            transferFundsPage.clickBtnTransfer();

            test.log(Status.INFO, "Complete Transfer");

            String actualMessageTransfer = transferFundsPage.getHSuccessTransfer();

            if (actualMessageTransfer.equals("Transfer Complete!")) {
                test.log(Status.PASS, "Verify 'Transfer Complete!' message is visible");
            } else {
                test.log(Status.FAIL, "FAIL VALIDATION: The successfully transfer funds message is not visible or not correct");
                captureScreenshot(test, "FAIL_MESSAGE_TRANSFER_FUNDS", driver);
            }


            assertEquals("The success message 'Transfer Complete!' should be visible and correct", "Transfer Complete!", actualMessageTransfer);

        } catch (Exception e) {
            test.log(Status.FAIL, "FAIL TEST TRANSFER_FUNDS: " + e.getMessage());
            captureScreenshot(test, "FAIL_TRANSFER_FUNDS", driver);
            String actualMessageTransfer = transferFundsPage.getHSuccessTransfer();
            assertEquals("The success message 'Transfer Complete!' should be visible and correct", "Transfer Complete!", actualMessageTransfer);
        }
    }


        @AfterAll
        public void close() throws InterruptedException {
            driver.quit();
            extent.flush();
        }

}
