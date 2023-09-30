package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CustomersListPage;

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
    @Test(description = "Correct search customer in Edge browser")
    public final void searchCustomerAutoTest() {
        SoftAssert softAssert = new SoftAssert();
        CustomersListPage listCustomers = new CustomersListPage(driver);
        listCustomers.pressButton();
        softAssert.assertTrue(listCustomers.checkWordInTable(),"Client not found");
        softAssert.assertAll();
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
