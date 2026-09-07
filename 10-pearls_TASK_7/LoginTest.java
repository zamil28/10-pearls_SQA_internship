package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

/**
 * Task 2: Login failure scenarios.
 * Task 3: Login success flow + homepage element validation.
 */
public class LoginTest extends BaseTest {

    // ---- Task 2: Login failure ----

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"standard_user", "wrong_password"},
                {"invalid_user", "secret_sauce"},
                {"", ""},
                {"locked_out_user", "secret_sauce"} // valid creds, but account is locked
        };
    }

    @Test(dataProvider = "invalidCredentials",
          description = "Invalid / locked-out login attempts should show an error and stay on login page")
    public void testLoginFailureShowsError(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingFailure(username, password);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Expected an error message for credentials: " + username + " / " + password);

        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(), "Error message text should not be empty");

        // Still on the login page, not redirected
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"),
                "User should NOT be redirected to inventory page on failed login");
    }

    // ---- Task 3: Login success + homepage validation ----

    @Test(description = "Valid credentials should log the user in and land on the Products homepage")
    public void testLoginSuccessAndHomepageElements() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "User should be redirected to the inventory/homepage after login");

        Assert.assertEquals(productsPage.getPageTitle(), "Products",
                "Homepage title should read 'Products'");

        Assert.assertTrue(productsPage.isNavMenuDisplayed(),
                "Navigation (burger) menu should be visible on homepage");

        Assert.assertTrue(productsPage.isCartIconDisplayed(),
                "Shopping cart icon should be visible on homepage");

        Assert.assertTrue(productsPage.getProductCount() > 0,
                "Homepage should list at least one product");
    }
}
