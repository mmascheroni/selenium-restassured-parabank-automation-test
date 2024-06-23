package Tests.api;

import Tests.reports.ReportFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import models.Account;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.ConfigProperties;

import java.util.List;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OpenNewAccountApiTest {
    static ConfigProperties configProperties = new ConfigProperties();

    static String openNewAccountUrl = configProperties.getProperty("test.api.url.openAccount.url");

    static String loginUrl = configProperties.getProperty("test.api.url.login");

    static String customerAccountsUrl = configProperties.getProperty("test.api.url.customer.accounts");

    static String username = configProperties.getProperty("test.username");

    static String password = configProperties.getProperty("test.password");

    private static ExtentSparkReporter info = new ExtentSparkReporter("reports/api/OpenNewAccountApiTest.html");
    private static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @Test
    public void openNewAccountTest() {
        RestAssured.defaultParser = Parser.JSON;
        ExtentTest test = extent.createTest("Post Open New Account");

        test.log(Status.INFO, "Test Start");
        test.log(Status.INFO, "Open New Account");

        Response resLogin = given()
                .contentType("application/json")
                .get(loginUrl + "/" + username + "/"+ password);

        String customerId = resLogin.xmlPath().getString("customer.id");

        Response resCustomerAccounts = given()
                .auth().basic(username, password)
                .contentType("application/json")
                .get(customerAccountsUrl + customerId + "/accounts");


        List<Account> accounts = resCustomerAccounts.jsonPath().getList(".", Account.class);

        Account firstAccount = accounts.get(0);
        int accountId = firstAccount.getId();

        Response res = given()
                .auth().basic(username, password)
                .contentType("application/json")
                .queryParam("customerId", customerId)
                .queryParam("newAccountType", "1")
                .queryParam("fromAccountId", accountId)
                .post(openNewAccountUrl);

        if ( res.getStatusCode() == 200 ) {
            test.log(Status.PASS, "SUCCESS TEST - POST Open New Account code is equals to 200");
        } else {
            test.log(Status.FAIL, "FAIL TEST - POST Open New Account code is not equals to 200");
        }

        Assert.assertEquals(200, res.getStatusCode());
    }


    @AfterAll
    public void close() {
        extent.flush();
    }


}
