package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    private By linkToGoRegister = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");

    private By inputFirstName = By.xpath("//*[@id=\"customer.firstName\"]");
    private By inputLastName = By.xpath("//*[@id=\"customer.lastName\"]");

    private By inputAddress = By.xpath("//*[@id=\"customer.address.street\"]");

    private By inputCity = By.xpath("//*[@id=\"customer.address.city\"]");

    private By inputState = By.xpath("//*[@id=\"customer.address.state\"]");

    private By inputZipCode = By.xpath("//*[@id=\"customer.address.zipCode\"]");

    private By inputPhoneNumber = By.xpath("//*[@id=\"customer.phoneNumber\"]");

    private By inputSSN = By.xpath("//*[@id=\"customer.ssn\"]");

    private By inputUsername = By.xpath("//*[@id=\"customer.username\"]");

    private By inputPassword = By.xpath("//*[@id=\"customer.password\"]");
    private By inputPasswordConfirm = By.xpath("//*[@id=\"repeatedPassword\"]");

    private By btnRegister = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");

    private By h1WelcomeUsername = By.xpath("//*[@id=\"rightPanel\"]/h1");

    private By pAccountCreatedSuccesfully = By.xpath("//*[@id=\"rightPanel\"]/p");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickLinkToGoRegisterForm() throws InterruptedException {
        click(linkToGoRegister);
    }


//    public boolean isFormPresent() {
//        if ( findElement(form).isDisplayed() ) {
//            return true;
//        }
//
//        return false;
//    }

    public void completeInputFirstName(String firstName) throws InterruptedException {
        sendText(firstName, inputFirstName);
    }

    public void completeInputLastName(String lastName) throws InterruptedException {
        sendText(lastName, inputLastName);
    }

    public void completeInputAddress(String address) throws InterruptedException {
        sendText(address, inputAddress);
    }

    public void completeInputCity(String city) throws InterruptedException {
        sendText(city, inputCity);
    }

    public void completeInputState(String state) throws InterruptedException {
        sendText(state, inputState);
    }

    public void completeInputZipCode(String zipCode) throws InterruptedException {
        sendText(zipCode, inputZipCode);
    }

    public void completeInputPhone(String phone) throws InterruptedException {
        sendText(phone, inputPhoneNumber);
    }

    public void completeInputSSN(String ssn) throws InterruptedException {
        sendText(ssn, inputSSN);
    }

    public void completeInputUsername(String username) throws InterruptedException {
        sendText(username, inputUsername);
    }

    public void completeInputPassword(String password) throws InterruptedException {
        sendText(password, inputPassword);
    }

    public void completeInputPasswordConfirm(String password) throws InterruptedException {
        sendText(password, inputPasswordConfirm);
    }

    public void clickRegisterBtn() throws InterruptedException {
        click(btnRegister);
    }

    public String getWelcomeUsername() throws InterruptedException {
        return getText(h1WelcomeUsername);
    }

    public String getMessageAccountCreated() throws InterruptedException {
        return getText(pAccountCreatedSuccesfully);
    }

}
