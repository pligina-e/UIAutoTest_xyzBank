package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

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

    public static void waitUntilVisibleAllElements(WebDriver driver, final List<WebElement> webElement) {
        getInstance(driver).wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    public static void waitThenClick(WebDriver driver, final WebElement webElement) {
        getInstance(driver).wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public static void writeText(WebDriver driver, final WebElement webElement, String text) {
        getInstance(driver).wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
    }

    public static String alertGetTextAndClick(WebDriver driver) {
        Alert alert = getInstance(driver).wait.until(alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
}
