import InventoryPage from "../pages/InventoryPage";
import ProductPage from "../pages/ProductPage";

describe("Product navigation and validation", () => {
  beforeEach(() => {
    cy.loginAsStandardUser();
  });

  it("displays product names and prices on the listing page", () => {
    InventoryPage.getAllProductNames().should("have.length.greaterThan", 0);

    InventoryPage.getAllProductPrices().each(($price) => {
      cy.wrap($price).invoke("text").should("match", /^\$\d+\.\d{2}$/);
    });
  });

  it("opens a specific product and validates its details", () => {
    const targetProduct = "Sauce Labs Backpack";

    InventoryPage.openProductByName(targetProduct);

    ProductPage.getProductName().should("have.text", targetProduct);
    ProductPage.getProductPrice().invoke("text").should("match", /^\$\d+\.\d{2}$/);
    ProductPage.getProductDescription().should("not.be.empty");
  });

  it("adds a product to the cart from its details page", () => {
    InventoryPage.openFirstProduct();

    ProductPage.addToCart();
    ProductPage.isAddedToCart().should("be.visible");

    cy.assertCartCount(1);
  });

  it("navigates back from the product details page to the listing", () => {
    InventoryPage.openProductByName("Sauce Labs Bike Light");

    ProductPage.goBackToProducts();

    cy.url().should("include", "/inventory.html");
    InventoryPage.getPageTitle().should("have.text", "Products");
  });

  it("adds multiple products to cart directly from the listing using a custom command", () => {
    cy.addProductToCartByIndex(0);
    cy.addProductToCartByIndex(1);

    cy.assertCartCount(2);
  });

  it("sorts products by price low to high and verifies order", () => {
    InventoryPage.sortBy("Price (low to high)");

    InventoryPage.getAllProductPrices()
      .then(($prices) => {
        const prices = [...$prices].map((el) =>
          parseFloat(el.innerText.replace("$", ""))
        );
        const sorted = [...prices].sort((a, b) => a - b);
        expect(prices).to.deep.equal(sorted);
      });
  });
});
