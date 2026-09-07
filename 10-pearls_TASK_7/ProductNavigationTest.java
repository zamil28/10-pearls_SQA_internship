package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import utils.BaseTest;

import java.util.List;

/**
 * Task 4: Navigate to product pages and validate product details
 * (name, price, description, availability).
 */
public class ProductNavigationTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod(dependsOnMethods = "setUp") // runs after BaseTest's driver setup
    public void loginFirst() {
        LoginPage loginPage = new LoginPage(driver);
        productsPage = loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Open a specific product and validate its details")
    public void testProductDetailsValidation() {
        String targetProduct = "Sauce Labs Backpack";

        ProductDetailsPage detailsPage = productsPage.openProductByName(targetProduct);

        Assert.assertEquals(detailsPage.getProductName(), targetProduct,
                "Product name on details page should match the clicked product");

        Assert.assertTrue(detailsPage.getProductPrice().startsWith("$"),
                "Product price should be displayed in dollar format");

        Assert.assertFalse(detailsPage.getProductDescription().isEmpty(),
                "Product description should not be empty");

        Assert.assertTrue(detailsPage.isAvailableToPurchase(),
                "Product should be available to add to cart");
    }

    @Test(description = "Add a product to cart from its details page and verify state changes")
    public void testAddToCartFromDetailsPage() {
        ProductDetailsPage detailsPage = productsPage.openFirstProduct();

        Assert.assertTrue(detailsPage.isAvailableToPurchase(), "Product should start as available to add");

        detailsPage.addToCart();

        Assert.assertTrue(detailsPage.isInCart(),
                "After adding to cart, the button should switch to a 'Remove' state");
    }

    @Test(description = "Navigate back from a product's details page to the product listing")
    public void testNavigateBackToProductListing() {
        List<String> namesBefore = productsPage.getAllProductNames();
        Assert.assertFalse(namesBefore.isEmpty(), "Listing page should show products before navigating");

        ProductDetailsPage detailsPage = productsPage.openProductByName(namesBefore.get(0));
        ProductsPage backOnListing = detailsPage.goBackToProducts();

        Assert.assertEquals(backOnListing.getPageTitle(), "Products",
                "Should be back on the Products listing page");
        Assert.assertTrue(backOnListing.getProductCount() > 0,
                "Product listing should still show products after navigating back");
    }
}
