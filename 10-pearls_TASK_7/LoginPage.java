package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionUtils;

/**
 * Page Object for the Login page (Task 6: POM).
 * Holds ONLY locators + actions for this page — no assertions here.
 * Assertions belong in the test classes.
 */
public class LoginPage {

    private final WebDriver driver;
    private final ActionUtils actions;

    // ---- Locators ----
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionUtils(driver);
    }

    // ---- Actions ----
    public LoginPage enterUsername(String username) {
        actions.type(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        actions.type(passwordField, password);
        return this;
    }

    /** Clicks login and returns the next Page Object (used for the success path). */
    public ProductsPage clickLogin() {
        actions.click(loginButton);
        return new ProductsPage(driver);
    }

    /** Convenience method for a full login attempt in one call (success path). */
    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    /** Use this variant when you expect login to FAIL, so no page-object hop happens. */
    public void loginExpectingFailure(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        actions.click(loginButton);
    }

    public boolean isErrorDisplayed() {
        return actions.isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return actions.getText(errorMessage);
    }
}
