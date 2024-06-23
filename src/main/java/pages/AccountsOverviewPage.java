package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsOverviewPage extends BasePage {

    private By linkToGoAccountsOverviewPage = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");

    private By pToValidate = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");

    private By linkFirstAccount = By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/a");

    private By hAccountDetailsToValidate = By.xpath("//*[@id=\"accountDetails\"]/h1");

    private By selectActivityPeriod = By.xpath("//*[@id=\"month\"]");

    private By selectType = By.xpath("//*[@id=\"transactionType\"]");

    private By btnGo = By.xpath("//*[@id=\"activityForm\"]/table/tbody/tr[3]/td[2]/input");

    private By tableTransactions = By.xpath("//*[@id=\"transactionTable\"]");

    private By pNoTransaction = By.xpath("//*[@id=\"noTransactions\"]");

    public AccountsOverviewPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickLinkToGoAccountsOverviewPage() throws InterruptedException {
        click(linkToGoAccountsOverviewPage);
    }

    public String getTextToValidate() throws InterruptedException {
        return getText(pToValidate);
    }

    public void clickFirstAccount() throws InterruptedException {
        click(linkFirstAccount);
    }

    public String getTextAccountDetailsToValidate() throws InterruptedException {
        return getText(hAccountDetailsToValidate);
    }

    public void selectActivityPeriod(String value) {
        selectFromDropdown(selectActivityPeriod, value);
    }

    public void selectType(String value) {
        selectFromDropdown(selectType, value);
    }

    public void clickBtnGo() throws InterruptedException {
        click(btnGo);
    }

    public boolean isTableVisible() {
        return isElementVisible(tableTransactions);
    }

    public boolean isPNoTransactionVisible() {
        return isElementVisible(pNoTransaction);
    }

}
