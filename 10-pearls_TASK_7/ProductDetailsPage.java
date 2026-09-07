package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionUtils;

/**
 * Page Object for an individual product's detail page
 * (Task 4: validate product name, price, description, availability).
 */
public class ProductDetailsPage {

    private final WebDriver driver;
    private final ActionUtils actions;

    // ---- Locators ----
    private final By productName   = By.className("inventory_details_name");
    private final By productPrice  = By.className("inventory_details_price");
    private final By productDesc   = By.className("inventory_details_desc");
    private final By addToCartBtn  = By.cssSelector("button[data-test^='add-to-cart']");
    private final By removeBtn     = By.cssSelector("button[data-test^='remove']");
    private final By backButton    = By.id("back-to-products");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionUtils(driver);
    }

    public String getProductName() {
        return actions.getText(productName);
    }

    public String getProductPrice() {
        return actions.getText(productPrice);
    }

    public String getProductDescription() {
        return actions.getText(productDesc);
    }

    /** "Availability" here = whether the item can still be added to cart (in stock). */
    public boolean isAvailableToPurchase() {
        return actions.isDisplayed(addToCartBtn);
    }

    public void addToCart() {
        actions.click(addToCartBtn);
    }

    public boolean isInCart() {
        return actions.isDisplayed(removeBtn);
    }

    public ProductsPage goBackToProducts() {
        actions.click(backButton);
        return new ProductsPage(driver);
    }
}
