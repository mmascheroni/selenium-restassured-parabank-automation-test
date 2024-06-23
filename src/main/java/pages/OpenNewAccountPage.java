package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenNewAccountPage extends BasePage {

    private By linkToGoOpenNewAccountPage = By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");

    private By selectTypeOfAccount = By.xpath("//*[@id=\"type\"]");

    private By selectFromAccountId = By.xpath("//*[@id=\"fromAccountId\"]");

    private By btnOpenNewAccount = By.xpath("//*[@id=\"openAccountForm\"]/form/div/input");

    private By pSuccessOpenAccount = By.xpath("//*[@id=\"openAccountResult\"]/p[1]");

    private By hSuccessOpenAccount = By.xpath("//*[@id=\"openAccountResult\"]/h1");

    private By linkNewAccountNumber = By.xpath("//*[@id=\"newAccountId\"]");

    public OpenNewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickLinkToGoNewOpenAccountPage() throws InterruptedException {
        click(linkToGoOpenNewAccountPage);
    }

    public void selectTypeOfAccount(String valueToSelect) {
        selectFromDropdown(selectTypeOfAccount, valueToSelect);
    }

    public void selectFromAccountId(String valueToSelect) {
        selectFromDropdown(selectFromAccountId, valueToSelect);
    }

    public void clickBtnOpenNewAccount() throws InterruptedException {
        click(btnOpenNewAccount);
    }

    public String getSuccessAccountOpenMessage() throws InterruptedException {
        return getText(pSuccessOpenAccount);
    }
}
