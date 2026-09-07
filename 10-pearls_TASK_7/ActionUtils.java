package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Reusable custom commands / utility methods.
 * Every Page Object uses these instead of calling raw WebDriver / findElement
 * directly, so waits and error handling live in ONE place (Task 5).
 */
public class ActionUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ActionUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /** Waits for an element to be visible, then returns it. */
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Waits for an element to be clickable, then clicks it. */
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /** Clears a field (if any pre-filled text) and types the given text. */
    public void type(By locator, String text) {
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    /** Returns the visible text of an element, waiting for it first. */
    public String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    /** Returns true if the element appears within the timeout, false otherwise. */
    public boolean isDisplayed(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Waits until at least one element matching the locator is present, returns the list. */
    public List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /** Selects a dropdown option by visible text (java.util.List dropdown, e.g. sort filters). */
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = waitForVisible(locator);
        new org.openqa.selenium.support.ui.Select(dropdown).selectByVisibleText(visibleText);
    }

    /** Waits until the current URL contains the given fragment (useful after navigation). */
    public void waitForUrlContains(String fragment) {
        wait.until(ExpectedConditions.urlContains(fragment));
    }
}
