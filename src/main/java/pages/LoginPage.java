package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private By inputUsername = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private By inputPassword = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");
    private By btnLogIn = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void completeInputUsername(String username) throws InterruptedException {
        sendText(username, inputUsername);
    }

    public void completeInputPassword(String password) throws InterruptedException {
        sendText(password, inputPassword);
    }

    public void clickBtnLogIn() throws InterruptedException {
        click(btnLogIn);
    }
}
