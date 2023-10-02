package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CustomersListPage;

import java.util.ArrayList;

/**
 * Данный класс нужен для того, чтобы проверить выполнение параллельности тестов
 * Тест этого класса запускается в браузере Edge
 */
public class TestForParallelism {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        driver.manage().window().maximize();
    }

    @Test(description = "Correct search customer")
    public final void searchCustomerAutoTest() {
        CustomersListPage listCustomers = new CustomersListPage(driver);
        listCustomers.pressButton();
        ArrayList<String> listText = listCustomers.threeColumnWordList();
        String customerSearchWord = listCustomers.randomWordFromTable(listText);
        Assert.assertTrue(listCustomers.checkWordInTable(customerSearchWord),"Client not found");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
