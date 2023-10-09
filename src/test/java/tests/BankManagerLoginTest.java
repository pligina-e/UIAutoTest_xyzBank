package tests;

import helpers.Generator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.AuthorizationPage;
import pages.CustomersListPage;

import java.util.ArrayList;

import static helpers.Wait.getTextAlertAndClick;

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
        AddCustomerPage addCustomer = new AddCustomerPage(driver);
        addCustomer.pressButton();
        addCustomer.fillFields(name, surname, postcode);
        Assert.assertTrue(getTextAlertAndClick(driver).contains("Customer added successfully with customer id"), "Alert has different text");
    }

    @Test(description = "Correct sorting of names")
    public final void sortByNameAutoTest() {
        CustomersListPage listCustomers = new CustomersListPage(driver);
        listCustomers.pressButton();
        ArrayList<String> listNamesForReverseOrder = listCustomers.listNames();
        ArrayList<String> reverseListNames = listCustomers.listNamesReverseOrder(listNamesForReverseOrder);
        listCustomers.clickOnFirstCell();
        ArrayList<String> listNames = listCustomers.listNames();
        Assert.assertEquals(reverseListNames, listNames, "Incorrect sorting");
    }

    @Test(description = "Correct search customer")
    public final void searchCustomerAutoTest() {
        CustomersListPage listCustomers = new CustomersListPage(driver);
        listCustomers.pressButton();
        ArrayList<String> listText = listCustomers.threeColumnWordList();
        String customerSearchWord = listCustomers.randomWordFromTable(listText);
        Assert.assertTrue(listCustomers.checkWordInTable(customerSearchWord),"Client not found");
    }

    @Test(description = "Correct choose customer")
    public final void authAutoTest() {
        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.pressButton();
        authorization.selectDataAndSubmit();
        Assert.assertTrue(getTextAlertAndClick(driver).contains("Account created successfully with account Number"), "Alert has different text");
    }
}
