package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.Wait.inputText;
import static helpers.Wait.waitThenClick;

/**
 * Класс для работы с формой создания клиента (Первый тест-кейс)
 * @see BasePage
 */
public class AddCustomerPage extends BasePage {
    @FindBy(xpath = "//*[@ng-class='btnClass1']")
    WebElement addCustomerButton;
    @FindBy(xpath = "//*[contains(@ng-model,'fName')]")
    WebElement firstNameField;
    @FindBy(xpath = "//*[contains(@ng-model,'lName')]")
    WebElement lastNameField;
    @FindBy(xpath = "//*[contains(@ng-model,'postCd')]")
    WebElement postCodeField;
    @FindBy(xpath = "//*[@class='btn btn-default']")
    WebElement submitButton;

    public AddCustomerPage(final WebDriver webDriver) {
        super(webDriver);
    }

    public AddCustomerPage pressButton() {
        waitThenClick(driver, addCustomerButton);
        return this;
    }

    /**
     * Метод для заполнения полей произвольными строками и нажатия кнопки "Add Customer"
     * @param name имя клиента
     * @param surname фамилия клиента
     * @param postcode почтовый индекс
     */
    @Step("Write name {name}, surname {surname} and post code {postcode} in the fields, then submit")
    public AddCustomerPage fillFields(String name, String surname, String postcode) {
        inputText(driver, firstNameField, name);
        inputText(driver, lastNameField, surname);
        inputText(driver, postCodeField, postcode);
        waitThenClick(driver, submitButton);
        return this;
    }
}
