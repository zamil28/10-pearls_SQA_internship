package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ActionUtils;

import java.util.List;

/**
 * Page Object for the Products / homepage screen shown right after a
 * successful login (Task 3: homepage validation, Task 4: product navigation).
 */
public class ProductsPage {

    private final WebDriver driver;
    private final ActionUtils actions;

    // ---- Locators ----
    private final By pageTitle       = By.className("title");               // "Products"
    private final By burgerMenuBtn   = By.id("react-burger-menu-btn");
    private final By shoppingCartIcon = By.className("shopping_cart_link");
    private final By inventoryItems  = By.className("inventory_item");
    private final By inventoryItemNames = By.className("inventory_item_name");
    private final By sortDropdown    = By.className("product_sort_container");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionUtils(driver);
    }

    // ---- Homepage validation actions (Task 3) ----
    public String getPageTitle() {
        return actions.getText(pageTitle);
    }

    public boolean isNavMenuDisplayed() {
        return actions.isDisplayed(burgerMenuBtn);
    }

    public boolean isCartIconDisplayed() {
        return actions.isDisplayed(shoppingCartIcon);
    }

    public int getProductCount() {
        return actions.waitForAllVisible(inventoryItems).size();
    }

    public void sortBy(String visibleText) {
        actions.selectDropdownByVisibleText(sortDropdown, visibleText);
    }

    // ---- Product navigation (Task 4) ----
    /** Clicks a product by its exact visible name and lands on the details page. */
    public ProductDetailsPage openProductByName(String productName) {
        List<WebElement> names = actions.waitForAllVisible(inventoryItemNames);
        for (WebElement el : names) {
            if (el.getText().trim().equalsIgnoreCase(productName)) {
                el.click();
                return new ProductDetailsPage(driver);
            }
        }
        throw new org.openqa.selenium.NoSuchElementException(
                "Product not found on listing page: " + productName);
    }

    /** Opens the first product in the listing (handy when the exact name doesn't matter). */
    public ProductDetailsPage openFirstProduct() {
        actions.click(inventoryItemNames);
        return new ProductDetailsPage(driver);
    }

    public List<String> getAllProductNames() {
        return actions.waitForAllVisible(inventoryItemNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
