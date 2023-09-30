package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static helpers.Wait.waitThenClick;
import static helpers.Wait.waitUntilVisibleAllElements;
import static helpers.Wait.writeText;

/**
 * Класс для работы с формой просмотра таблицы клиентов (Второй, третий тест-кейс)
 * @see BasePage
 */
public class CustomersListPage extends BasePage {
    @FindBy(xpath = "//*[@ng-class='btnClass3']")
    WebElement customersButton;
    @FindBy(xpath = "//*[contains(@ng-click,'fName')]")
    WebElement firstNameField;
    @FindBy(xpath = "//*[@class= 'ng-scope']/td[1]")
    List<WebElement> firstNameTableCells;
    @FindBy(xpath = "//*[@ng-model= 'searchCustomer']")
    WebElement searchCustomerField;
    @FindBy(xpath = "//*[@class= 'ng-binding']")
    List<WebElement> threeTableColumns;
    public CustomersListPage(final WebDriver webDriver) {
        super(webDriver);
    }
    @Step("Wait and press the button to add customer")
    public CustomersListPage pressButton() {
        waitThenClick(driver, customersButton);
        return this;
    }
    /**
     * Метод для нажатия на содержимое первой ячейки таблицы (для того, чтобы произошла сортировка)
     */
    @Step("Wait and click on the first cell for sorting rows")
    public CustomersListPage clickOnFirstCell() {
        waitThenClick(driver, firstNameField);
        return this;
    }
    /**
     * Метод для запоминания всех значений первого столбца таблицы
     * @return список имён
     */
    @Step("Remember all names from the table")
    public ArrayList<String> listNames() {
        waitUntilVisibleAllElements(driver, firstNameTableCells);
        ArrayList<String> listText = new ArrayList<>();
        for (int i = 0; i < firstNameTableCells.size(); i++) {
            listText.add(firstNameTableCells.get(i).getText());
        }
        return listText;
    }
    /**
     * Метод для сортировки списка имен в обратном алфавитном порядке
     * @see #listNames()
     * @return список имён в обратном алфавитном порядке
     */
    @Step("Remember all names sorted in reverse order")
    public ArrayList<String> listNamesReverseOrder() {
        ArrayList<String> listText = listNames();
        Comparator<String> reverseComparator = Comparator.reverseOrder();
        Collections.sort(listText, reverseComparator);
        return listText;
    }
    /**
     * Метод для запоминания значений трёх первых столбцов таблицы (необходимо для поиска клиента)
     * @return все значения из трёх первых столбцов
     */
    @Step("Remember values in three columns of the table")
    public ArrayList<String> threeColumnWordList() {
        waitUntilVisibleAllElements(driver, threeTableColumns);
        ArrayList<String> listText = new ArrayList<>();
        for (int i = 0; i < threeTableColumns.size(); i++) {
            listText.add(threeTableColumns.get(i).getText());
        }
        return listText;
    }
    /**
     * Метод для случайного выбора слова из списка значений трёх первых столбцов и его ввода в поле (поиск клиента)
     * @see #threeColumnWordList()
     * @return слово
     */
    @Step("Select a random word from the list and write it in the field")
    public String randomWordFromTable() {
        Random random = new Random();
        ArrayList<String> listText = threeColumnWordList();
        String randomWord = listText.get(random.nextInt(listText.size()));
        writeText(driver, searchCustomerField, randomWord);
        return randomWord;
    }
    /**
     * Метод для проверки того, остались ли в табл. строки, содержащие введённое слово в поле "Search Customer" (слово выбрано случайным образом)
     * @see #randomWordFromTable()
     * @return булевское значение
     */
    @Step("Check the remaining rows for the content of the entered random word in the field")
    public boolean checkWordInTable() {
        String customerSearchWord = randomWordFromTable();
        boolean containsWord;
        waitUntilVisibleAllElements(driver, threeTableColumns);
        for (int i = 0; i < threeTableColumns.size(); i++) {
            containsWord = threeTableColumns.get(i).getText().contains(customerSearchWord);
            if (containsWord) return true;
        }
        return false;
    }
}
