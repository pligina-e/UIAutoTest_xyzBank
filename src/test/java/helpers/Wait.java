package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Wait {
    private static Wait instance;
    private final WebDriverWait wait;

    private static Wait getInstance(WebDriver driver) {
        if (instance== null) {
            instance = new Wait(driver);
        }
        return instance;
    }

    public Wait(WebDriver driver) {
        int timeout = Integer.parseInt(PropertyProvider.getInstance().getProperty("explicit.timeout"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static void waitUntilVisibleElement(WebDriver driver, final WebElement webElement) {
        getInstance(driver).wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilVisibleAllElements(WebDriver driver, final List<WebElement> webElement) {
        getInstance(driver).wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    public static void waitThenClick(WebDriver driver, final WebElement webElement) {
        getInstance(driver).wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    @Step("Write text in the field")
    public static void inputText(WebDriver driver, final WebElement webElement, String text) {
        getInstance(driver).wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
    }

    @Step("Get alert text and click")
    public static String getTextAlertAndClick(WebDriver driver) {
        Alert alert = getInstance(driver).wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
}
