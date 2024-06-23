package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage extends BasePage {

    private By linkToGoTransferFundsPage = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");

    private By inputAmount = By.xpath("//*[@id=\"amount\"]");

    private By inputSelectFromAccount = By.xpath("//*[@id=\"fromAccountId\"]");

    private By inputSelectToAccount = By.xpath("//*[@id=\"toAccountId\"]");

    private By btnTransfer = By.xpath("//*[@id=\"transferForm\"]/div[2]/input");

    private By hSuccessTranfer = By.xpath("//*[@id=\"showResult\"]/h1");

    public TransferFundsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickLinkToGoTransferFundsPage() throws InterruptedException {
        click(linkToGoTransferFundsPage);
    }

    public void sendAmount(String amount) throws InterruptedException {
        sendKey(amount, inputAmount);
    }

    public void selectFromAccount(int index) {
        selectFromDropdownByIndex(inputSelectFromAccount, index);
    }

    public void selectToAccount(int index) {
        selectFromDropdownByIndex(inputSelectToAccount, index);
    }

    public void clickBtnTransfer() throws InterruptedException {
        click(btnTransfer);
    }

    public String getHSuccessTransfer() throws InterruptedException {
        return getText(hSuccessTranfer);
    }
}
