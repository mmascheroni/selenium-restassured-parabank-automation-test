package Tests.api;

import Tests.reports.ReportFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.ConfigProperties;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterApiTest {

    static ConfigProperties configProperties = new ConfigProperties();

    static String username = configProperties.getProperty("test.username");

    static String password = configProperties.getProperty("test.password");

    static String registerUrl = configProperties.getProperty("test.api.url.register");


    private static ExtentSparkReporter info = new ExtentSparkReporter("reports/api/RegisterApiTest.html");
    private static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }


    @Test
    public void getRegisterPage() {
        ExtentTest test = extent.createTest("Get Register Page");

        test.log(Status.INFO, "Test Start");
        test.log(Status.INFO, "Navigate to the app - Register Page");


        Response res = given()
                .contentType("application/json")
                .get(registerUrl);

        if ( res.getStatusCode() == 200 ) {
            test.log(Status.PASS, "SUCCESS TEST - GET Register Page code is equals to 200");
        } else {
            test.log(Status.FAIL, "FAIL TEST - GET Register Page code is not equals to 200");
        }

        Assert.assertEquals(200, res.getStatusCode());
    }

    @Test
    public void postRegisterPage() {
        ExtentTest test = extent.createTest("Post Register");

        test.log(Status.INFO, "Test Start");
        test.log(Status.INFO, "POST - Register");


        Response res = given()
                .cookie("JSESSIONID=35C5260D636641DC6C6F0485E4FED581; Path=/parabank; HttpOnly;")
                .contentType("application/x-www-form-urlencoded")
                .formParam("customer.firstName", "Prueba")
                .formParam("customer.lastName", "Prueba")
                .formParam("customer.address.street", "Prueba")
                .formParam("customer.address.city", "Prueba")
                .formParam("customer.address.state", "Prueba")
                .formParam("customer.address.zipCode", "Prueba")
                .formParam("customer.phoneNumber", "Prueba")
                .formParam("customer.ssn", "Prueba")
                .formParam("customer.username", username)
                .formParam("customer.password", password)
                .formParam("repeatedPassword1", password)
                .post(registerUrl);

        if ( res.getStatusCode() == 200 ) {
            test.log(Status.PASS, "SUCCESS TEST - POST Register code is equals to 200");
        } else {
            test.log(Status.FAIL, "FAIL TEST - POST Register code is not equals to 200");
        }

        Assert.assertEquals(200, res.getStatusCode());
    }

    @AfterAll
    public void close() {
        extent.flush();
    }

}
