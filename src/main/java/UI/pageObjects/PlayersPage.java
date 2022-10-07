package UI.pageObjects;

import API.dataProviders.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PlayersPage extends BasePage {

    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c1']/a")
    public WebElement orderByUserNameButton;
    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c2']/a")
    public WebElement orderByExternalIDButton;
    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c3']/a")
    public WebElement orderByNameButton;
    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c4']/a")
    public WebElement orderByLastNameButton;
    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c5']/a")
    public WebElement orderByEmailButton;
    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c6']/a")
    public WebElement orderByPhoneButton;
    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c7']/a")
    public WebElement orderByHallButton;
    @FindBy(xpath = "//th[@id='payment-system-transaction-grid_c9']/a")
    public WebElement orderByRegDateButton;

    @FindBy(xpath = "(//tr[@class='odd']/td[not(@class)]/a)[1]")
    public WebElement ExpectedUserName;
    @FindBy(xpath = "//*[text()='10015e6f7e109a9cd']")
    public WebElement ExpectedExternalID;
    @FindBy(xpath = "//td[@class = '__web-inspector-hide-shortcut__'][1]")
    public WebElement ExpectedName;
    @FindBy(xpath = "//td[@class = 'hide-mobile'][3]")
    public WebElement ExpectedLastName;
    @FindBy(xpath = "//td[@class='hide-mobile'][4]")
    public WebElement ExpectedEmail;
    @FindBy(xpath = " //*[text() = '+996559693918']")
    public WebElement ExpectedPhone;
    @FindBy(xpath = "//td[@class = '__web-inspector-hide-shortcut__'][2]")
    public WebElement ExpectedHall;
    @FindBy(xpath = " //*[text() = '2019-05-13 10:00:52']")
    public WebElement ExpectedRegDate;


    public int clickOrderButton() {
        int i = 0;
        switch (ConfigReader.getProperty("orderBy").toLowerCase()) {
            case "username":
                helper.click(orderByUserNameButton);
                i = 1;
                break;
            case "external id":
                helper.click(orderByExternalIDButton);
                i = 2;
                break;
            case "name":
                System.out.println(ConfigReader.getProperty("orderBy"));
                helper.click(orderByNameButton);
                i = 3;
                break;
            case "last name":
                helper.click(orderByLastNameButton);
                i = 4;
                break;
            case "e-mail":
                helper.click(orderByEmailButton);
                i = 5;
                break;
            case "phone":
                helper.click(orderByPhoneButton);
                i = 6;
                break;
            case "hall":
                helper.click(orderByHallButton);
                i = 10;
                break;
            case "registered date":
                helper.click(orderByRegDateButton);
                i = 11;
                break;
            default:
                System.out.println("Неверное название поля для сортировки");
                ;
                break;
        }

        helper.pause(3000);
        return i;
    }
}
