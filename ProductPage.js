/**
 * Page Object for an individual product's detail page.
 * Task 4: validate product name, price, description, availability.
 */
class ProductPage {
  // ---- Locators ----
  elements = {
    productName: () => cy.get(".inventory_details_name"),
    productPrice: () => cy.get(".inventory_details_price"),
    productDesc: () => cy.get(".inventory_details_desc"),
    addToCartBtn: () => cy.get("button[data-test^='add-to-cart']"),
    removeBtn: () => cy.get("button[data-test^='remove']"),
    backButton: () => cy.get("#back-to-products"),
  };

  getProductName() {
    return this.elements.productName();
  }

  getProductPrice() {
    return this.elements.productPrice();
  }

  getProductDescription() {
    return this.elements.productDesc();
  }

  addToCart() {
    this.elements.addToCartBtn().click();
    return this;
  }

  isAddedToCart() {
    return this.elements.removeBtn();
  }

  goBackToProducts() {
    this.elements.backButton().click();
    return this;
  }
}

export default new ProductPage();
