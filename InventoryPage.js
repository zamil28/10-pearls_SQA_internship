/**
 * Page Object for the Products / inventory page (the homepage after login).
 * Task 3: homepage validation. Task 4: product listing navigation.
 */
class InventoryPage {
  // ---- Locators ----
  elements = {
    pageTitle: () => cy.get(".title"),
    burgerMenuBtn: () => cy.get("#react-burger-menu-btn"),
    shoppingCartIcon: () => cy.get(".shopping_cart_link"),
    inventoryItems: () => cy.get(".inventory_item"),
    inventoryItemNames: () => cy.get(".inventory_item_name"),
    inventoryItemPrices: () => cy.get(".inventory_item_price"),
    sortDropdown: () => cy.get(".product_sort_container"),
    addToCartButtons: () => cy.get("button[data-test^='add-to-cart']"),
  };

  // ---- Homepage validation actions (Task 3) ----
  getPageTitle() {
    return this.elements.pageTitle();
  }

  isNavMenuVisible() {
    return this.elements.burgerMenuBtn();
  }

  isCartIconVisible() {
    return this.elements.shoppingCartIcon();
  }

  getProductCount() {
    return this.elements.inventoryItems();
  }

  sortBy(visibleText) {
    this.elements.sortDropdown().select(visibleText);
    return this;
  }

  // ---- Product navigation (Task 4) ----
  /** Clicks a product by its exact visible name and lands on the product detail page. */
  openProductByName(productName) {
    this.elements.inventoryItemNames().contains(productName).click();
    return this;
  }

  openFirstProduct() {
    this.elements.inventoryItemNames().first().click();
    return this;
  }

  getAllProductNames() {
    return this.elements.inventoryItemNames();
  }

  getAllProductPrices() {
    return this.elements.inventoryItemPrices();
  }

  addFirstProductToCart() {
    this.elements.addToCartButtons().first().click();
    return this;
  }
}

export default new InventoryPage();
