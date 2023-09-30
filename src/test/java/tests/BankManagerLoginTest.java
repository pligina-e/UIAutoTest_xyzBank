package tests;

import helpers.Generator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;
import pages.CustomersListPage;

import java.util.ArrayList;

public class BankManagerLoginTest extends BaseTest {
    Generator generator = new Generator();

    @DataProvider(name = "Valid data to create a customer")
    public Object[][] dpMethod() {
        return new Object[][] {
                { generator.getName(), generator.getSurname(), generator.getPostcode() }
        };
    }

    @Test(description = "Add customer with correct data", dataProvider = "Valid data to create a customer")
    public final void correctDataAutoTest(String name, String surname, String postcode) {
        SoftAssert softAssert = new SoftAssert();
        AddCustomerPage addCustomer = new AddCustomerPage(driver);
        addCustomer.pressButton();
        addCustomer.fillFields(name, surname, postcode);
        softAssert.assertTrue(addCustomer.alertText().contains("Customer added successfully with customer id"), "Alert has different text");
        softAssert.assertAll();
    }

    @Test(description = "Correct sorting of names")
    public final void sortByNameAutoTest() {
        SoftAssert softAssert = new SoftAssert();
        CustomersListPage listCustomers = new CustomersListPage(driver);
        listCustomers.pressButton();
        ArrayList<String> reverseListNames = listCustomers.listNamesReverseOrder();
        listCustomers.clickOnFirstCell();
        ArrayList<String> listNames = listCustomers.listNames();
        softAssert.assertEquals(reverseListNames, listNames, "Incorrect sorting");
        softAssert.assertAll();
    }

    @Test(description = "Correct search customer")
    public final void searchCustomerAutoTest() {
        SoftAssert softAssert = new SoftAssert();
        CustomersListPage listCustomers = new CustomersListPage(driver);
        listCustomers.pressButton();
        softAssert.assertTrue(listCustomers.checkWordInTable(),"Client not found");
        softAssert.assertAll();
    }
}
